package utility;

import models.AllowedVehicle;
import models.enums.VehicleType;

import java.util.List;

public class CheckCapacityAvailability
{
    public static AllowedVehicle findAvailableCapacity(List<AllowedVehicle> allowedVehicles, VehicleType vehicleType)
    {
        if(allowedVehicles == null)
            return  null;

        for(AllowedVehicle allowedVehicle:allowedVehicles)
        {
            if(allowedVehicle.getVehicleType()==vehicleType && allowedVehicle.getAvailableCapacity()>0)
                return  allowedVehicle;
        }
        return null;
    }
}
