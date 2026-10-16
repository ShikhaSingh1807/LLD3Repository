package dtos;

import models.ParkingSpot;

import java.time.LocalDateTime;

public class GenerateTicketResponseDTO
{
    private Long ticketId;
    private String ticketNumber;
    private LocalDateTime entry_time;
    private ParkingSpot parkingSpot;
    private String message;
    private ResponseStatus resrponseStatus;

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public LocalDateTime getEntry_time() {
        return entry_time;
    }

    public void setEntry_time(LocalDateTime entry_time) {
        this.entry_time = entry_time;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseStatus getResrponseStatus() {
        return resrponseStatus;
    }

    public void setResrponseStatus(ResponseStatus resrponseStatus) {
        this.resrponseStatus = resrponseStatus;
    }
}
