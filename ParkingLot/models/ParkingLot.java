package models;

import models.enums.AllowedVehicleType;
import models.enums.ParkingLotStatus;
import models.enums.PricingStrategyTypes;
import models.enums.SpotAllocationStrategyType;


import java.util.List;

public class ParkingLot extends BaseModal
{
    private String name;
    private ParkingLotStatus status;
    private List<Gate> gates;
    private List<ParkingFloor> parkingFloors;
    private List<AllowedVehicleType> allowedVehicleTypes;
    private PricingStrategyTypes pricingStrategyType;
    private SpotAllocationStrategyType spotAllocationStrategyType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParkingLotStatus getStatus() {
        return status;
    }

    public void setStatus(ParkingLotStatus status) {
        this.status = status;
    }

    public List<Gate> getGates() {
        return gates;
    }

    public void setGates(List<Gate> gates) {
        this.gates = gates;
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    public void setParkingFloors(List<ParkingFloor> parkingFloors) {
        this.parkingFloors = parkingFloors;
    }

    public List<AllowedVehicleType> getAllowedVehicleTypes() {
        return allowedVehicleTypes;
    }

    public void setAllowedVehicleTypes(List<AllowedVehicleType> allowedVehicleTypes) {
        this.allowedVehicleTypes = allowedVehicleTypes;
    }

    public PricingStrategyTypes getPricingStrategyType() {
        return pricingStrategyType;
    }

    public void setPricingStrategyType(PricingStrategyTypes pricingStrategyType) {
        this.pricingStrategyType = pricingStrategyType;
    }

    public SpotAllocationStrategyType getSpotAllocationStrategyType() {
        return spotAllocationStrategyType;
    }

    public void setSpotAllocationStrategyType(SpotAllocationStrategyType spotAllocationStrategyType) {
        this.spotAllocationStrategyType = spotAllocationStrategyType;
    }
}
