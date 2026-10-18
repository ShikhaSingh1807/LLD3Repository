package strategies.pricingStrategy;

import models.enums.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class HourlyPricingStrategy implements PricingStrategy
{
    //Now here we will need hourly data for each vehicle . Data Structire map
    //Bile : 50 , CAR : 100 , TRUCK : 200
    private Map<VehicleType,Double> feesPerVehicleType;

    public HourlyPricingStrategy()
    {
        this.feesPerVehicleType = new HashMap<>();
        //Value for each vehicle type can be taken from envt file or property file
        //feesPerVehicleType.put(VehicleType.CAR,env.get(CAR_HOURLY_RATE));
        feesPerVehicleType.put(VehicleType.BIKE,50.0);
        feesPerVehicleType.put(VehicleType.CAR,100.0);
        feesPerVehicleType.put(VehicleType.TRUCK,200.0);
    }
    @Override
    public Double calculatePrice(LocalDateTime entry_time, LocalDateTime exit_time, VehicleType vehicleType)
    {
        if (entry_time == null || exit_time == null || vehicleType == null) {
            return 0.0; // Edge-case handling
        }

        Duration duration = Duration.between(entry_time,exit_time);

        // Extract the difference in different units
        double hours = duration.toMinutes()/60.0;

        return feesPerVehicleType.getOrDefault(vehicleType,0.0)*hours;
    }
}
