package models;

import models.enums.GateStatus;
import models.enums.GateType;

public class Gate extends  BaseModal
{
    private GateStatus gateStatus;
    private Integer gateNumber;
    private GateType gateType;
    private Operator operator;
    private ParkingLot parkingLot;//from operator id get the gate id and from gate id get parking lot id

    public Gate(GateStatus gateStatus, Integer gateNumber, GateType gateType) {
        this.gateStatus = gateStatus;
        this.gateNumber = gateNumber;
        this.gateType = gateType;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public GateStatus getGateStatus() {
        return gateStatus;
    }

    public void setGateStatus(GateStatus gateStatus) {
        this.gateStatus = gateStatus;
    }

    public Integer getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(Integer gateNumber) {
        this.gateNumber = gateNumber;
    }

    public GateType getGateType() {
        return gateType;
    }

    public void setGateType(GateType gateType) {
        this.gateType = gateType;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
