package main.client;

import adapter.FakePaymentGateway;
import controller.BillController;
import controller.PaymentController;
import controller.TicketController;
import dtos.*;
import models.*;
import models.enums.PaymentMode;
import models.enums.VehicleType;
import repository.InMemoryRepository;
import service.BillService;
import service.PaymentService;
import service.TicketService;
import service.VehicleService;

public class Client
{
    static void main()
    {
        //Creating All the Repository
        InMemoryRepository<ParkingLot> parkingLotInMemoryRepository = new InMemoryRepository<>();
        InMemoryRepository<ParkingFloor> parkingFloorInMemoryRepository = new InMemoryRepository<>();
        InMemoryRepository<ParkingSpot> parkingSpotInMemoryRepository = new InMemoryRepository<>();
        InMemoryRepository<Gate> gateInMemoryRepository = new InMemoryRepository<>();
        InMemoryRepository<Operator> operatorInMemoryRepository = new InMemoryRepository<>();
        InMemoryRepository<AllowedVehicle> allowedVehicleInMemoryRepository = new InMemoryRepository<>();
        InMemoryRepository<Ticket> ticketInMemoryRepository = new InMemoryRepository<>();
        InMemoryRepository<Vehicle> vehicleInMemoryRepository = new InMemoryRepository<>();
        InMemoryRepository<Bill> billInMemoryRepository = new InMemoryRepository<>();

        //Calling Data generator to get pre populated details in DB
        DataGenerator dataGenerator = new DataGenerator(parkingLotInMemoryRepository,parkingFloorInMemoryRepository,
                parkingSpotInMemoryRepository,gateInMemoryRepository,operatorInMemoryRepository,
                allowedVehicleInMemoryRepository);

        dataGenerator.generateData();

        //Initiating Service class as they will be used
        VehicleService vehicleService = new VehicleService(vehicleInMemoryRepository);
        TicketService ticketService = new TicketService(operatorInMemoryRepository,
                vehicleService,parkingSpotInMemoryRepository,ticketInMemoryRepository);
        BillService billService = new BillService(ticketInMemoryRepository,billInMemoryRepository,operatorInMemoryRepository);
        PaymentService paymentService = new PaymentService(billInMemoryRepository,
                new FakePaymentGateway(),parkingSpotInMemoryRepository);

        //Initiating  TicketController class
        TicketController ticketController = new TicketController(ticketService);
        BillController billController = new BillController(billService);
        PaymentController paymentController = new PaymentController(paymentService);

        //Issue ticket when car enters
        IssueTicketRequestDTO ticketRequestDTO =
                new IssueTicketRequestDTO(8L,"MH-46-34523","Shikha",
                        "9867543423", VehicleType.CAR);
        IssueTicketResponseDTO ticketResponseDTO = ticketController.issueTicket(ticketRequestDTO);
        System.out.println(ticketResponseDTO.getMessage()+" | Ticket Id : "+ticketResponseDTO.getTicketNumber()
                +" | Parking Spot : "+ticketResponseDTO.getParkingSpot().getParkingSpotNumber());


        //Generate Bill for the ticket when leaving
        GenerateBillRequestDTO billRequestDTO = new GenerateBillRequestDTO(ticketResponseDTO.getTicketId(),9L);
        GenerateBillResponseDTO billResponseDTO = billController.generateBill(billRequestDTO);
        System.out.println(billResponseDTO.getMessage()+" with amount : "+billResponseDTO.getBillAmount()
                +" at : "+billResponseDTO.getBillGeneratedDateTime().toLocalDate());

        //We will need to make Bill status as paid

        MakePaymentRequestDTO paymentRequest = new MakePaymentRequestDTO(billResponseDTO.getBillId(), PaymentMode.DC,billResponseDTO.getBillAmount());
        MakePaymentResponseDTO makePaymentResponse = paymentController.makePayment(paymentRequest);
        System.out.println("Payment status: " + makePaymentResponse.getMessage());

        System.out.println("Spot status now: " + ticketResponseDTO.getParkingSpot().getParkingSpotStatus());

    }
}
