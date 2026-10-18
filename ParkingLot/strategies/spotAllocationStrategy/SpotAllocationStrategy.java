package strategies.spotAllocationStrategy;

import models.ParkingLot;
import models.ParkingSpot;
import models.enums.VehicleType;

import java.util.List;

public interface SpotAllocationStrategy
{
    public ParkingSpot allocateParkingSpot(List<ParkingSpot> parkingSpots , VehicleType vehicleType);
}
