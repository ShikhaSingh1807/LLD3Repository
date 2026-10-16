package models;

import models.enums.AllowedVehicleType;
import models.enums.ParkingSpotStatus;

public class ParkingSpot extends BaseModal
{
    private ParkingSpotStatus parkingSpotStatus;
    private String parkingSpotNumber;
    private AllowedVehicleType allowedVehicleType;

    public ParkingSpotStatus getParkingSpotStatus() {
        return parkingSpotStatus;
    }

    public void setParkingSpotStatus(ParkingSpotStatus parkingSpotStatus) {
        this.parkingSpotStatus = parkingSpotStatus;
    }

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public AllowedVehicleType getAllowedVehicleType() {
        return allowedVehicleType;
    }

    public void setAllowedVehicleType(AllowedVehicleType allowedVehicleType) {
        this.allowedVehicleType = allowedVehicleType;
    }
}
