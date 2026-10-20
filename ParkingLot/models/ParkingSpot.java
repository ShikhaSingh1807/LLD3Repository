package models;

import models.enums.VehicleType;
import models.enums.ParkingSpotStatus;

public class ParkingSpot extends BaseModal
{
    private ParkingSpotStatus parkingSpotStatus;
    private Integer parkingSpotNumber;
    private VehicleType vehicleTypes;

    public ParkingSpot(Integer parkingSpotNumber, VehicleType vehicleTypes) {
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

    public Integer getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(Integer parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public VehicleType getAllowedVehicleType() {
        return vehicleTypes;
    }

    public void setAllowedVehicleType(VehicleType vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }
}
