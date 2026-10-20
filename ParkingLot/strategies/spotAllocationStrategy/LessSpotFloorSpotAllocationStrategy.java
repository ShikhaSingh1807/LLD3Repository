package strategies.spotAllocationStrategy;

import models.AllowedVehicle;
import models.ParkingFloor;
import models.ParkingLot;
import models.ParkingSpot;
import models.enums.ParkingFloorStatus;
import models.enums.ParkingSpotStatus;
import models.enums.VehicleType;
import repository.InMemoryRepository;
import utility.CheckCapacityAvailability;

import java.util.List;
import java.util.Optional;

//In this Strategy basically that floor is selected which has minimum Slot and if 2 floors have same number of slot
//then the floor with less floor number should be selected. In selected floor nearest available spot is selected .
//Nearest means spot with least spot number
//If a floor is Operation then only check further else skip to next floor
public class LessSpotFloorSpotAllocationStrategy implements  SpotAllocationStrategy
{
    private InMemoryRepository<ParkingSpot> parkingSpotInMemoryRepository;

    public LessSpotFloorSpotAllocationStrategy(InMemoryRepository<ParkingSpot> parkingSpotInMemoryRepository)
    {
        this.parkingSpotInMemoryRepository=parkingSpotInMemoryRepository;
    }

    @Override
    public Optional<ParkingSpot> allocateParkingSpot(ParkingLot parkingLot, VehicleType vehicleType)
    {
        Optional<ParkingSpot> optionalParkingSpot = Optional.empty();
        synchronized (parkingLot)
        {
            //Step 1 :- Check if the Parking Lot have capacity for the provided vehicle type

            AllowedVehicle parkingLotAllowedVehicle = CheckCapacityAvailability
                    .findAvailableCapacity(parkingLot.getAllowed_vehicles(), vehicleType);

            if (parkingLotAllowedVehicle == null)
                throw new RuntimeException("Parking Lot do not have capacity for the provided vehicle type...");


            //Step 2 :- Check if floors are configured for the parking lot
            if (parkingLot.getParkingFloors() == null) {
                throw new RuntimeException("Parking Floors are not available for this parking lot ...");
            }

            //Now we will assign some common variable
            int minimumAvailableCount = Integer.MAX_VALUE;
            ParkingFloor selectFloor = null;
            AllowedVehicle selectedFloorAllowedVehicle = null;
            AllowedVehicle floorAllowedVehicle = null;


            //Now iterate over all the floors and check which floor has capacity and accordingly we will be finding
            //floor with lowest spot capacity

            for(ParkingFloor floor : parkingLot.getParkingFloors())
            {
                //Check if the floor ois Operational or not else if not operational go to next floor
                if(floor.getParkingFloorStatus() != ParkingFloorStatus.OPEN)
                    continue;

                //Check if current floor has capacity for given vehicle type
                floorAllowedVehicle = CheckCapacityAvailability
                        .findAvailableCapacity(floor.getAllowedVehicles(),vehicleType);

                if(floorAllowedVehicle ==null)
                {
                    //this floor has no capacity so go to next floor
                    continue;
                }

                //current available capacity of the floor
                int availableCapacity = floorAllowedVehicle.getAvailableCapacity();

                //  Select the floor with the fewest available spots.
                // On a tie, select the floor with the lower floor number.
                if(availableCapacity > 0
                && (availableCapacity < minimumAvailableCount
                || (availableCapacity == minimumAvailableCount
                && ( selectFloor == null
                || ( floor.getFloorNumber() < selectFloor.getFloorNumber() )))))
                {
                    selectFloor = floor;
                    minimumAvailableCount = availableCapacity;
                    selectedFloorAllowedVehicle = floorAllowedVehicle;
                }

            }

            //After going through each floor we dont get a desirable floor then retirn empty optional
            if(selectFloor == null)
                return Optional.empty();

            //If we have got a parking floor now simply we can look for nearest parking spot in that parking floor

            optionalParkingSpot = allocateParkingSpotFortheFloor(selectFloor,vehicleType);

            if(optionalParkingSpot.isPresent())
            {
                ParkingSpot parkingSpot = optionalParkingSpot.get();

                parkingSpot.setParkingSpotStatus(ParkingSpotStatus.FILLED);

                parkingSpotInMemoryRepository.save(parkingSpot);

                parkingLotAllowedVehicle.setAvailableCapacity(parkingLotAllowedVehicle.getAvailableCapacity() - 1);
                selectedFloorAllowedVehicle.setAvailableCapacity(selectedFloorAllowedVehicle.getAvailableCapacity() - 1);

            }
        }
        return optionalParkingSpot;
    }

    private Optional<ParkingSpot> allocateParkingSpotFortheFloor(ParkingFloor selectFloor, VehicleType vehicleType)
    {
        ParkingSpot selectedSpot = null;

        if(selectFloor.getParkingSpots() == null)
            return Optional.empty();

        for(ParkingSpot parkingSpot: selectFloor.getParkingSpots())
        {
            if(parkingSpot.getParkingSpotStatus()== ParkingSpotStatus.EMPTY &&  parkingSpot.getAllowedVehicleType()==vehicleType)
            {
                if(selectedSpot == null || parkingSpot.getParkingSpotNumber() < selectedSpot.getParkingSpotNumber())
                    selectedSpot=parkingSpot;
            }
        }

        return  Optional.ofNullable(selectedSpot);
    }
}
