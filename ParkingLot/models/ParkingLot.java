package models;

import models.enums.VehicleType;
import models.enums.ParkingLotStatus;
import models.enums.PricingStrategyTypes;
import models.enums.SpotAllocationStrategyType;


import java.util.List;

public class ParkingLot extends BaseModal
{
    private String name;
    private ParkingLotStatus status;

    //Although all gates are same in behaviour still to access it will be easy for us if we go for separate list
    private List<Gate> entry_gates;//create object and attach so we will not be putting in constructor
    private List<Gate> exit_gates;//create object and attach so we will not be putting in constructor
    private List<ParkingFloor> parkingFloors;//create object and attach so we will not be putting in constructor
    private List<AllowedVehicle> allowed_vehicles; //{BIKE:20 , CAR:12} //create object and attach so we will not be putting in constructor
    private PricingStrategyTypes pricingStrategyType;//send through the constructor and set it
    private SpotAllocationStrategyType spotAllocationStrategyType;//send through the constructor and set it

    public ParkingLot(String name,PricingStrategyTypes pricingStrategyTypes,SpotAllocationStrategyType spotAllocationStrategyType)
    {
        this.name = name;
        this.status = ParkingLotStatus.EMPTY;
        this.pricingStrategyType= pricingStrategyTypes;
        this.spotAllocationStrategyType=spotAllocationStrategyType;
    }

    public List<Gate> getEntry_gates() {
        return entry_gates;
    }

    public void setEntry_gates(List<Gate> entry_gates) {
        this.entry_gates = entry_gates;
    }

    public List<Gate> getExit_gates() {
        return exit_gates;
    }

    public void setExit_gates(List<Gate> exit_gates) {
        this.exit_gates = exit_gates;
    }

    public List<AllowedVehicle> getAllowed_vehicles() {
        return allowed_vehicles;
    }

    public void setAllowed_vehicles(List<AllowedVehicle> allowed_vehicles) {
        this.allowed_vehicles = allowed_vehicles;
    }

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

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    public void setParkingFloors(List<ParkingFloor> parkingFloors) {
        this.parkingFloors = parkingFloors;
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
