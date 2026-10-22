package dtos;

import java.time.LocalDateTime;

public class GenerateBillResponseDTO
{
    private Long billId;
    private Integer billNumber;
    private Double billAmount;
    private LocalDateTime billGeneratedDateTime;//exit time
    private String message;
    private ResponseStatus status;


    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Integer getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(Integer billNumber) {
        this.billNumber = billNumber;
    }

    public Double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }

    public LocalDateTime getBillGeneratedDateTime() {
        return billGeneratedDateTime;
    }

    public void setBillGeneratedDateTime(LocalDateTime billGeneratedDateTime) {
        this.billGeneratedDateTime = billGeneratedDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
}
