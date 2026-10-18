package strategies.spotAllocationStrategy;

import models.ParkingSpot;
import models.enums.VehicleType;

import java.util.List;

public class ManualSpotAllocationStrategy implements  SpotAllocationStrategy
{
    @Override
    public ParkingSpot allocateParkingSpot(List<ParkingSpot> parkingSpots, VehicleType vehicleType) {
        return null;
    }
}
