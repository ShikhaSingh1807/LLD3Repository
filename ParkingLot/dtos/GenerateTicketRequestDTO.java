package dtos;

import models.enums.AllowedVehicleType;

public class GenerateTicketRequestDTO
{
    private Long operatorId;
    private String registrationNumber;
    private String ownerName;
    private String ownerContactNumber;
    private AllowedVehicleType vehicleType;

    public GenerateTicketRequestDTO(Long operatorId, String registrationNumber, String ownerName,
                                    String ownerContactNumber, AllowedVehicleType vehicleType) {
        this.operatorId = operatorId;
        this.registrationNumber = registrationNumber;
        this.ownerName = ownerName;
        this.ownerContactNumber = ownerContactNumber;
        this.vehicleType = vehicleType;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerContactNumber() {
        return ownerContactNumber;
    }

    public void setOwnerContactNumber(String ownerContactNumber) {
        this.ownerContactNumber = ownerContactNumber;
    }

    public AllowedVehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(AllowedVehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
