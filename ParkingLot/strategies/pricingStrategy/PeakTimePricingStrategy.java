package strategies.pricingStrategy;

import models.enums.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//The main logic that will be used to calculate the bill amount here would be the actual amount + 10% additional
public class PeakTimePricingStrategy implements PricingStrategy
{
    private Map<VehicleType,Double> feesPerVehicleType;
    private static Integer multiplier = 10;

    public PeakTimePricingStrategy()
    {
        this.feesPerVehicleType = new ConcurrentHashMap<>();
        feesPerVehicleType.put(VehicleType.CAR,100.0);
        feesPerVehicleType.put(VehicleType.TRUCK,200.0);
        feesPerVehicleType.put(VehicleType.BIKE,50.0);

    }
    @Override
    public Double calculatePrice(LocalDateTime entry_time, LocalDateTime exit_time, VehicleType vehicleType)
    {
        if(entry_time==null || exit_time == null || vehicleType==null)
            return 0.0;

        Duration duration = Duration.between(entry_time,exit_time);

        double hoursSpent = duration.toMinutes()/60.0;

        return hoursSpent*feesPerVehicleType.getOrDefault(vehicleType,0.0)*1.1;
    }
}
