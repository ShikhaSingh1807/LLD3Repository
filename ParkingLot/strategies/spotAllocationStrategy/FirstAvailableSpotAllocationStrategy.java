package strategies.spotAllocationStrategy;

import models.AllowedVehicle;
import models.ParkingFloor;
import models.ParkingLot;
import models.ParkingSpot;
import models.enums.ParkingSpotStatus;
import models.enums.VehicleType;
import repository.InMemoryRepository;
import utility.CheckCapacityAvailability;
import java.util.List;
import java.util.Optional;

public class FirstAvailableSpotAllocationStrategy implements SpotAllocationStrategy
{
    private InMemoryRepository<ParkingSpot> parkingSpotInMemoryRepository;

    public FirstAvailableSpotAllocationStrategy(InMemoryRepository<ParkingSpot> parkingSpotInMemoryRepository)
    {
        this.parkingSpotInMemoryRepository=parkingSpotInMemoryRepository;
    }

    @Override
    public Optional<ParkingSpot> allocateParkingSpot(ParkingLot parkingLot, VehicleType vehicleType)
    {
        synchronized (parkingLot) {


            //Step 1 :- Check if the Parking Lot have capacity for the provided vehicle type

            AllowedVehicle parkingLotAllowedVehicle = CheckCapacityAvailability
                    .findAvailableCapacity(parkingLot.getAllowed_vehicles(), vehicleType);

            if (parkingLotAllowedVehicle == null)
                throw new RuntimeException("Parking Lot do not have capacity for the provided vehicle type...");

            //Step 2 :- Check if floors are configured for the parking lot
            if (parkingLot.getParkingFloors() == null) {
                throw new RuntimeException("Parking Floors are not available for this parking lot ...");
            }

            Optional<ParkingSpot> optParkingSpot = Optional.empty();

            //Step 3 :- Now iterate over all the floors and check if that floor have capacity
            for (ParkingFloor parkingFloor : parkingLot.getParkingFloors()) {
                AllowedVehicle floorAllowedVehicle = CheckCapacityAvailability
                        .findAvailableCapacity(parkingFloor.getAllowedVehicles(), vehicleType);

                if (floorAllowedVehicle == null) {
                    //This floor has no capacity so go to next floor to check if it has space
                    continue;
                }

                optParkingSpot = allocateParkingSpotFortheFloor(parkingFloor.getParkingSpots(), vehicleType);

                if (optParkingSpot.isEmpty()) {
                    //try to find in next floor
                    continue;
                }

                //Here the parking spot is found so now we will be changing its status
                ParkingSpot parkingSpot = optParkingSpot.get();
                parkingSpot.setParkingSpotStatus(ParkingSpotStatus.FILLED);

                //Update the updated status in the repository
                parkingSpotInMemoryRepository.save(parkingSpot);

                //Reduce the capacity details in lot and floor
                parkingLotAllowedVehicle.setAvailableCapacity(parkingLotAllowedVehicle.getAvailableCapacity() - 1);
                floorAllowedVehicle.setAvailableCapacity(floorAllowedVehicle.getAvailableCapacity() - 1);

                break;
            }
            return optParkingSpot;
        }
    }

    private Optional<ParkingSpot> allocateParkingSpotFortheFloor(List<ParkingSpot> parkingSpots,VehicleType vehicleType)
    {
        if(parkingSpots==null)
            return Optional.empty();

        for(ParkingSpot parkingSpot:parkingSpots)
        {
            if(parkingSpot.getParkingSpotStatus()==ParkingSpotStatus.EMPTY && parkingSpot.getAllowedVehicleType()==vehicleType)
                return Optional.ofNullable(parkingSpot);
        }

        return Optional.empty();
    }
}
