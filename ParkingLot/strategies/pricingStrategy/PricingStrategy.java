package strategies.pricingStrategy;

import models.enums.VehicleType;

import java.time.LocalDateTime;

public interface PricingStrategy
{
     Double calculatePrice(LocalDateTime entry_time , LocalDateTime exit_time , VehicleType vehicleType);
}
