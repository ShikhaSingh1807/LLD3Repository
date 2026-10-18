package service;

import factories.ParkingSpotAllocationStrategyFactory;
import models.*;
import models.enums.*;
import repository.InMemoryRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenerateTicketService
{
    private InMemoryRepository<Operator> operatorInMemoryRepository ;
    private VehicleService vehicleService;
    private InMemoryRepository<ParkingSpot> parkingSpotInMemoryRepository;
    private InMemoryRepository<Ticket> ticketInMemoryRepository;


    public GenerateTicketService(InMemoryRepository<Operator> operatorInMemoryRepository, VehicleService vehicleService,
                                 InMemoryRepository<ParkingSpot> parkingSpotInMemoryRepository,
                                 InMemoryRepository<Ticket> ticketInMemoryRepository)
    {
        this.operatorInMemoryRepository = operatorInMemoryRepository;
        this.vehicleService = vehicleService;
        this.parkingSpotInMemoryRepository = parkingSpotInMemoryRepository;
        this.ticketInMemoryRepository = ticketInMemoryRepository;
    }

    public Ticket generateTicket(Long operatorId, String registrationNumber, String ownerName, String ownerContactNumber,
                                 VehicleType vehicleType)
    {
        //TODO: Creating ticket  logic go's here
        Ticket ticket = new Ticket();

        //Get the operator object from operator_id
        //Check if operator exists - Do null check

        Optional<Operator> optOperator = operatorInMemoryRepository.getById(operatorId);
        if(optOperator.isEmpty())
        {
            throw  new RuntimeException("Operator not found with id : "+operatorId);
        }
        Operator operator = optOperator.get();

        //Get the gate object from the operator
        //Check if gate exists and if exist then  it should be an entry gate which is operational and OPEN
        //If exit gate then throw error
        Gate gate = operator.getGate();

        if(gate == null)
            throw new RuntimeException("Gate not found...");

        if( gate.getGateType() == GateType.EXIT)
            throw  new IllegalArgumentException("Invalid gate to generate ticket");

        if(! (gate.getGateStatus()== GateStatus.OPEN))
            throw new IllegalArgumentException("Invalid gate status .. Ticket can be generated at Open gate only..");



        //Get the registration Number of the vehicle and check in the repo if this vehicle record exist
        //If exist then get that vehicle record from the repo
        //But If it does not exist then create vehicle object from input and save in repo
        //For this we can use function Find or Create vehicle
        Vehicle vehicle = vehicleService.findOrCreate(registrationNumber,ownerName,ownerContactNumber,vehicleType);


        //From Gate object we will get Parking lot object
        //Check if Parking Lot is operational and open
        ParkingLot parkingLot = gate.getParkingLot();

        if(parkingLot==null)
            throw new RuntimeException("Parking Lot not found...");

        if((parkingLot.getStatus() == ParkingLotStatus.CLOSED) || (parkingLot.getStatus()==ParkingLotStatus.FILLED) ||
                (parkingLot.getStatus() == ParkingLotStatus.NON_OPERATIONAL))
            throw new IllegalArgumentException("Parking Lot not available for parking...");

        //Get Parking Floors from parking Lot and loop through it
        //In a floor which has no capacity for provided vehicle types we can simply skip that parking floor
        //The Parking floor which has space for the vehicle type we can get the parking spots of that parking floor



        //We can fetch the spot allocation strategy from the Parking lot object and we will also get list of Parking floors
        //to search for the empty slot

        //======================CRITICAL SECTION STARTS HERE=============================
        ParkingSpot availableParkingSpot = null;


        synchronized (this)
        {
            // STEP 1: Check parking lot capacity for this vehicle type

            AllowedVehicle lotAllowedVehicle  = findAvailableCapacity(parkingLot.getAllowed_vehicles(),vehicle.getVehicleType());

            if(lotAllowedVehicle==null || lotAllowedVehicle.getAvailableCapacity()<=0)
                throw new RuntimeException("Parking Lot does not have capacity to accommodate a "+vehicle.getVehicleType());

            // STEP 2: Check floors one by one
            if (parkingLot.getParkingFloors() == null) {
                throw new RuntimeException("Parking floors are not configured.");
            }

            for(ParkingFloor parkingFloor : parkingLot.getParkingFloors())
            {
                AllowedVehicle floorAllowedVehicle = findAvailableCapacity(parkingFloor.getAllowedVehicles(),vehicle.getVehicleType());

                if(floorAllowedVehicle==null || floorAllowedVehicle.getAvailableCapacity()<=0)
                    continue;//move to next floor as current floor has no capacity for given vehicle

                // STEP 3: Search spots only on this eligible floor
                availableParkingSpot = ParkingSpotAllocationStrategyFactory
                        .getSpotAllocationStrategy(parkingLot.getSpotAllocationStrategyType())
                        .allocateParkingSpot(parkingFloor.getParkingSpots(), vehicle.getVehicleType());

                // Continue to the next floor if no compatible empty
                // spot was found on the current floor.
                if (availableParkingSpot == null) {
                    continue;
                }

                // STEP 4: Reserve the spot
                availableParkingSpot.setParkingSpotStatus(
                        ParkingSpotStatus.FILLED
                );

                parkingSpotInMemoryRepository.save(availableParkingSpot);

                // STEP 5: Update both capacity counters
                floorAllowedVehicle.setAvailableCapacity(floorAllowedVehicle.getAvailableCapacity()-1);

                lotAllowedVehicle.setAvailableCapacity(lotAllowedVehicle.getAvailableCapacity()-1);

                // A suitable spot has been allocated.
                break;

            }

            // STEP 6: No suitable spot was found on any eligible floor
            if (availableParkingSpot == null) {
                throw new RuntimeException("No suitable parking spot available for "+ vehicle.getVehicleType());
            }

        }
        //================CRITICAL SECTION ENDS====================================
        //create ticket object
        ticket.setOperator(operator);
        ticket.setParkingSpot(availableParkingSpot);
        ticket.setGate(gate);
        ticket.setVehicle(vehicle);
        ticket.setEntry_time(LocalDateTime.now());
        //add the ticket object in repo
        return ticketInMemoryRepository.save(ticket);
    }

    //Method to check if the allowed vehicle list of lot or floor has the capacity for the current vehicle type to accommodate4
    //In case not available

    private AllowedVehicle findAvailableCapacity(List<AllowedVehicle> allowedVehicles,VehicleType vehicleType)
    {
        if(allowedVehicles == null)
            return null;

        for(AllowedVehicle allowedVehicle:allowedVehicles)
        {
            if(allowedVehicle.getVehicleType()==vehicleType && allowedVehicle.getAvailableCapacity()>0)
                return allowedVehicle;
        }
        return null;
    }
}

// operator - id|  name | etc ... | gateId  (We have to query gate table with gateId)
//ORM handles it (We can configure as eager loading or Lazy loading)
//If we have configured eager loading then when we query operator table using operator id
// operator - id|  name | etc ... | gateId (makes an internal call to gate table and also fetches to gate object and populates operator object)
