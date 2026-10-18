package strategies.spotAllocationStrategy;

import models.ParkingSpot;
import models.enums.ParkingSpotStatus;
import models.enums.VehicleType;

import java.util.List;

public class RandomSpotAllocationStrategy implements SpotAllocationStrategy
{
    @Override
    public ParkingSpot allocateParkingSpot(List<ParkingSpot> parkingSpots, VehicleType vehicleType)
    {
        for(ParkingSpot parkingSpot : parkingSpots)
        {
            if(parkingSpot.getParkingSpotStatus()== ParkingSpotStatus.EMPTY
                    && parkingSpot.getAllowedVehicleType()== vehicleType)
            {
                return parkingSpot;
            }
        }

        return null;
    }
}
