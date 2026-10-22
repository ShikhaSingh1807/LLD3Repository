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

        //Get Ticket from ticket Id
        Ticket ticket = ticketInMemoryRepository.getById(ticketId).orElseThrow(()->
                new RuntimeException("No Ticket found with id : "+ticketId));
        //Get Operator from operator id
        Operator operator = operatorInMemoryRepository.getById(operatorId).orElseThrow(()->
                new RuntimeException("No operator available with operatorId : "+operatorId));

        //Get gate from Operator object
        Gate gate = operator.getGate();

        //Check if gate is exit gate or not else throw error
        if(gate.getGateType() == GateType.ENTRY)
            throw new RuntimeException("Invalid gate and Bill can be generated only at Exit gate");

        //Check if gate was Open or not operational
        if(gate.getGateStatus()== GateStatus.CLOSED || gate.getGateStatus()==GateStatus.UN_OPERATIONAL)
            throw new IllegalArgumentException("Invalid Gate as Bill can be generated at Open Gare only");


        //Get Parking Lot from Gate
        ParkingLot parkingLot = gate.getParkingLot();

        if(parkingLot==null)
        {
            throw new RuntimeException("No Parking Lot found for parking with this gate details...");
        }

        //System.out.println("Will start Amount calculation");
        //For testing easy lets pass the exit time as 2 hours after the minutes
//        Double amountBilled = PricingStrategyFactory.getPricingStrategy(parkingLot.getPricingStrategyType())
//                .calculatePrice(ticket.getEntry_time(),LocalDateTime.now(),ticket.getVehicle().getVehicleType());
        Double amountBilled = PricingStrategyFactory.getPricingStrategy(parkingLot.getPricingStrategyType())
                .calculatePrice(ticket.getEntry_time(),LocalDateTime.now().plusHours(2),ticket.getVehicle().getVehicleType());
        //System.out.println("Amount : "+amountBilled);

        bill.setPaymentList(new ArrayList<>());
        bill.setBillStatus(BillStatus.UNPAID);
        bill.setTicket(ticket);
        bill.setGate(gate);
        bill.setOperator(operator);
        bill.setExit_time(LocalDateTime.now());
        bill.setAmount(amountBilled);

        return billInMemoryRepository.save(bill);
    }
}
