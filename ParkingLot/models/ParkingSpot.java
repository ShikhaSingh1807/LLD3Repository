package models;

import models.enums.VehicleType;
import models.enums.ParkingSpotStatus;

public class ParkingSpot extends BaseModal
{
    private ParkingSpotStatus parkingSpotStatus;
    private String parkingSpotNumber;
    private VehicleType vehicleTypes;

    public ParkingSpot(String parkingSpotNumber, VehicleType vehicleTypes) {
        this.parkingSpotStatus = ParkingSpotStatus.EMPTY;
        this.parkingSpotNumber = parkingSpotNumber;
        this.vehicleTypes = vehicleTypes;
    }

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

    public VehicleType getAllowedVehicleType() {
        return vehicleTypes;
    }

    public void setAllowedVehicleType(VehicleType vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }
}
