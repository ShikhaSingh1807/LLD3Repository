package strategies.pricingStrategy;

import models.enums.VehicleType;

import java.time.LocalDateTime;

public interface PricingStrategy
{
    public Double calculatePrice(LocalDateTime entry_time , LocalDateTime xit_time , VehicleType vehicleType);
}
