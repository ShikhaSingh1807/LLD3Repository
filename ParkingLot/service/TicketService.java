package service;

import factories.ParkingSpotAllocationStrategyFactory;
import models.*;
import models.enums.*;
import repository.InMemoryRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class TicketService
{
    private InMemoryRepository<Operator> operatorInMemoryRepository ;
    private VehicleService vehicleService;
    private InMemoryRepository<ParkingSpot> parkingSpotInMemoryRepository;
    private InMemoryRepository<Ticket> ticketInMemoryRepository;

    public TicketService(InMemoryRepository<Operator> operatorInMemoryRepository, VehicleService vehicleService,
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


       //Now we can use any of the strategy to allocate the parking spot


        Optional<ParkingSpot> optParkingSpot = ParkingSpotAllocationStrategyFactory
                        .getSpotAllocationStrategy(parkingLot.getSpotAllocationStrategyType(),parkingSpotInMemoryRepository)
                        .allocateParkingSpot(parkingLot,vehicle.getVehicleType());

        if(optParkingSpot.isEmpty())
        {
            throw new RuntimeException("No Parking Spot available...");
        }

        //create ticket object
        ticket.setOperator(operator);
        ticket.setParkingSpot(optParkingSpot.get());
        ticket.setGate(gate);
        ticket.setVehicle(vehicle);
        ticket.setEntry_time(LocalDateTime.now());
        //add the ticket object in repo
        return ticketInMemoryRepository.save(ticket);
    }

    //Method to check if the allowed vehicle list of lot or floor has the capacity for the current vehicle type to accommodate4
    //In case not available


}

// operator - id|  name | etc ... | gateId  (We have to query gate table with gateId)
//ORM handles it (We can configure as eager loading or Lazy loading)
//If we have configured eager loading then when we query operator table using operator id
// operator - id|  name | etc ... | gateId (makes an internal call to gate table and also fetches to gate object and populates operator object)
