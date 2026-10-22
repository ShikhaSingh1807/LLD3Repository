package service;

import adapter.PaymentGateWay;
import models.*;
import models.enums.*;
import repository.InMemoryRepository;
import utility.CheckCapacityAvailability;

import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentService
{
    private InMemoryRepository<Bill> billInMemoryRepository;
    private PaymentGateWay paymentGateWay;
    private InMemoryRepository<ParkingSpot> parkingSpotInMemoryRepository;

    public PaymentService(InMemoryRepository<Bill> billInMemoryRepository, PaymentGateWay paymentGateWay, InMemoryRepository<ParkingSpot> parkingSpotInMemoryRepository) {
        this.billInMemoryRepository = billInMemoryRepository;
        this.paymentGateWay = paymentGateWay;
        this.parkingSpotInMemoryRepository = parkingSpotInMemoryRepository;
    }

    public Payment makePayment(Long billId , PaymentMode paymentMode, Double amountToBePaid)
    {
        Payment payment = new Payment();
        //Get bill from the bill id

        Bill bill = billInMemoryRepository.getById(billId).orElseThrow(()->
                new RuntimeException("No Bill found in record with id : "+billId)) ;



        payment.setPaymentMode(paymentMode);
        payment.setPaymentTime(LocalDateTime.now());
        payment.setAmount(amountToBePaid);
        payment.setBill(bill);
        payment.setPaymentStatus(PaymentStatus.IN_PROGRESS);

        if(paymentMode != PaymentMode.CASH)
        {
            String transactionId = UUID.randomUUID().toString().replace("-","");
            boolean trnxStatus = paymentGateWay.processOnlinePayment(amountToBePaid,transactionId);
            payment.setTxn_id(transactionId);
            if(trnxStatus)
            {
                payment.setPaymentStatus(PaymentStatus.SUCCESS);
                payment.setMessage("Payment Completed");
            }
            else
            {
                payment.setPaymentStatus(PaymentStatus.FAILURE);
                payment.setMessage("Payment Failed");

            }

        }
        else
        {
            //CASH Payment
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
            payment.setMessage("Payment Completed");

        }

        //Add payment in bill's list of payments
        bill.getPaymentList().add(payment);

        //Now check if total Amount of bill is paid or not
        double totalBillAmountPaid = bill.getPaymentList().stream().filter(p->p.getPaymentStatus()==PaymentStatus.SUCCESS)
                .mapToDouble(Payment::getAmount).sum();

        if(totalBillAmountPaid >= bill.getAmount())
        {
            //We will need to make Bill status as paid
            bill.setBillStatus(BillStatus.PAID);
            //now we need to release the parking spot
            ParkingSpot parkingSpot = bill.getTicket().getParkingSpot();
            parkingSpot.setParkingSpotStatus(ParkingSpotStatus.EMPTY);

            parkingSpotInMemoryRepository.save(parkingSpot);
            //We will need to increase the allowed vehicle count for parkinglot and parking floor both
            ParkingLot parkingLot = bill.getGate().getParkingLot();

            if(parkingLot==null)
            {
                throw  new RuntimeException("No Parking Lot found associated with this bill...");
            }

            //Get the Particular Vehicle type Allowed vehicle
            VehicleType billedVehicleVehicleType= bill.getTicket().getVehicle().getVehicleType();
            AllowedVehicle parkingLotAllowedVehicle = CheckCapacityAvailability
                    .findAvailableCapacity(parkingLot.getAllowed_vehicles(),billedVehicleVehicleType);

            //Increment by one
            parkingLotAllowedVehicle.setAvailableCapacity(parkingLotAllowedVehicle.getAvailableCapacity()+1);


            //Now get Floor AllowedVehicle
            ParkingFloor parkingFloor = parkingSpot.getParkingFloor();

            AllowedVehicle parkingFloorAllowedVehicle = CheckCapacityAvailability
                    .findAvailableCapacity(parkingFloor.getAllowedVehicles(),billedVehicleVehicleType);
            parkingFloorAllowedVehicle.setAvailableCapacity(parkingLotAllowedVehicle.getAvailableCapacity()+1);

        }
        billInMemoryRepository.save(bill);
        return  payment;
    }
}
