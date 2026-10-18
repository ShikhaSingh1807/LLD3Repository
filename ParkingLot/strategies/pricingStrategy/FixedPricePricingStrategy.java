package strategies.pricingStrategy;

import models.enums.VehicleType;

import java.time.LocalDateTime;

public class FixedPricePricingStrategy implements PricingStrategy
{
    @Override
    public Double calculatePrice(LocalDateTime entry_time, LocalDateTime xit_time, VehicleType vehicleType) {
        return 0.0;
    }
}
