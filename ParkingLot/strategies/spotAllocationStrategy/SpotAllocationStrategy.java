package strategies.spotAllocationStrategy;

import models.ParkingLot;
import models.ParkingSpot;
import models.enums.VehicleType;

import java.util.List;
import java.util.Optional;

public interface SpotAllocationStrategy
{
    public Optional<ParkingSpot> allocateParkingSpot(ParkingLot parkingLot,VehicleType vehicleType);
}
