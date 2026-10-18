package service;


//Bill -> billNumber , gate , operator , ticket , List<Payment> , Bill Status , exit time , amount
import factories.PricingStrategyFactory;
import models.*;
import models.enums.BillStatus;
import models.enums.GateStatus;
import models.enums.GateType;
import repository.InMemoryRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class BillService
{
    private InMemoryRepository<Ticket> ticketInMemoryRepository;
    private InMemoryRepository<Bill> billInMemoryRepository;
    private InMemoryRepository<Operator> operatorInMemoryRepository;

    public BillService(InMemoryRepository<Ticket> ticketInMemoryRepository, InMemoryRepository<Bill> billInMemoryRepository,
                       InMemoryRepository<Operator> operatorInMemoryRepository)
    {
        this.ticketInMemoryRepository = ticketInMemoryRepository;
        this.billInMemoryRepository = billInMemoryRepository;
        this.operatorInMemoryRepository = operatorInMemoryRepository;
    }

    //Operator Id need to pass as operator info we get from ticket can be different from operator who is generating bill
    public Bill generateBill(Long ticketId,Long operatorId)
    {
        Bill bill = new Bill();

        //Using the Operator id get the operator object
        //Check for null
        Optional<Operator> optOperator = operatorInMemoryRepository.getById(operatorId);
        if(optOperator.isEmpty())
            throw new RuntimeException("No operator found with id : "+operatorId);

        Operator operator = optOperator.get();

        //From operator get the gate object
        //If gate object is null then throw exception
        //Also check type of gate , it should be exit gate then only generate bill else throw exception
        //We can check for gate status to be open to generate bill
        Gate gate = operator.getGate();
        if(gate==null)
            throw  new RuntimeException("Gate not found...");
        if(gate.getGateType()== GateType.ENTRY)
            throw  new IllegalArgumentException("Invalid Gate type . Bill can be generated at exit gate only...");
        if(gate.getGateStatus()== GateStatus.CLOSED || gate.getGateStatus()==GateStatus.UN_OPERATIONAL)
            throw new IllegalArgumentException("Invalid Gate as Bill can be generated at Open Gare only");

        //Get the ticket details from the ticketId
        //Check for null also

        Optional<Ticket> optTicket = ticketInMemoryRepository.getById(ticketId);
        if(optTicket.isEmpty())
        {
            throw  new RuntimeException("Ticket does not exists with id : "+ticketId);
        }

        Ticket ticket = optTicket.get();


        //From gate get the parking lot object
        //If parking lot object is null then throw exception
        //Get the pricing strategy for the parking lot and accordingly calculate the amount.
        ParkingLot parkingLot = gate.getParkingLot();
        if(parkingLot == null)
        {
            throw  new RuntimeException("Parking Lot not available...");
        }

        double amount = PricingStrategyFactory
                .getPricingStrategy(parkingLot.getPricingStrategyType())
                .calculatePrice(ticket.getEntry_time(), LocalDateTime.now(),ticket.getVehicle().getVehicleType());


        //Bill Status can be kept as UNPAID
        bill.setBillStatus(BillStatus.UNPAID);
        bill.setAmount(amount);
        bill.setGate(gate);
        bill.setOperator(operator);
        bill.setExit_time(LocalDateTime.now());
        bill.setTicket(ticket);
        bill.setPaymentList(new ArrayList<>());

        //Bill can be created with empty payments list

        return  billInMemoryRepository.save(bill);
    }
}
