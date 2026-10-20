package factories;

import models.ParkingSpot;
import models.enums.SpotAllocationStrategyType;
import repository.InMemoryRepository;
import strategies.spotAllocationStrategy.LessSpotFloorSpotAllocationStrategy;
import strategies.spotAllocationStrategy.FirstAvailableSpotAllocationStrategy;
import strategies.spotAllocationStrategy.SpotAllocationStrategy;

public class ParkingSpotAllocationStrategyFactory
{
    public static SpotAllocationStrategy getSpotAllocationStrategy(SpotAllocationStrategyType spotAllocationStrategyType, InMemoryRepository<ParkingSpot> parkingSpotInMemoryRepository)
    {
        return switch (spotAllocationStrategyType)
        {
            case FIRST_AVAILABLE -> new FirstAvailableSpotAllocationStrategy(parkingSpotInMemoryRepository);
            case LESS_SPOT_FLOOR -> new LessSpotFloorSpotAllocationStrategy(parkingSpotInMemoryRepository);
        };
    }
}
