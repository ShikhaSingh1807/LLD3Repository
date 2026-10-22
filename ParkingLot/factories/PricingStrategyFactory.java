package factories;

import models.enums.PricingStrategyTypes;
import strategies.pricingStrategy.HourlyPricingStrategy;
import strategies.pricingStrategy.PeakTimePricingStrategy;
import strategies.pricingStrategy.PricingStrategy;

public class PricingStrategyFactory
{
    public static PricingStrategy getPricingStrategy(PricingStrategyTypes pricingStrategyTypes)
    {
        return switch (pricingStrategyTypes)
        {
            case HOURLY -> new HourlyPricingStrategy();
            case PEAK_TIME -> new PeakTimePricingStrategy();
        };
    }
}
