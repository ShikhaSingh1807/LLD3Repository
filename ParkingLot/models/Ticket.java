package models;

import java.time.LocalDateTime;

public class Ticket extends BaseModal
{
    private String ticketNumber;
    private LocalDateTime entry_time;
    private Gate gate;
    private Operator operator;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot; // From Parking Spot we can get parking floor details
    private static Integer count=0;

    public Ticket()
    {
        setTicketNumber(String.valueOf(++count)); // Human readable number  -
        //In the Production we use Unique Number Generator
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

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
}
