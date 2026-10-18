package factories;

import models.enums.SpotAllocationStrategyType;
import strategies.spotAllocationStrategy.ManualSpotAllocationStrategy;
import strategies.spotAllocationStrategy.RandomSpotAllocationStrategy;
import strategies.spotAllocationStrategy.SpotAllocationStrategy;

public class ParkingSpotAllocationStrategyFactory
{
    public static SpotAllocationStrategy getSpotAllocationStrategy(SpotAllocationStrategyType spotAllocationStrategyType)
    {
        return switch (spotAllocationStrategyType)
        {
            case RANDOM -> new RandomSpotAllocationStrategy();
            case MANUAL -> new ManualSpotAllocationStrategy();
        };
    }
}
