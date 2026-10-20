package main.client;

import controller.TicketController;
import dtos.GenerateTicketRequestDTO;
import dtos.GenerateTicketResponseDTO;
import models.*;
import models.enums.VehicleType;
import repository.InMemoryRepository;
import service.TicketService;
import service.VehicleService;

public class Client
{
    static void main()
    {
        InMemoryRepository<ParkingLot> parkingLotInMemoryRepository = new InMemoryRepository<>();
        InMemoryRepository<ParkingFloor> parkingFloorInMemoryRepository = new InMemoryRepository<>();
        InMemoryRepository<ParkingSpot> parkingSpotInMemoryRepository = new InMemoryRepository<>();
        InMemoryRepository<Gate> gateInMemoryRepository = new InMemoryRepository<>();
        InMemoryRepository<Operator> operatorInMemoryRepository = new InMemoryRepository<>();
        InMemoryRepository<AllowedVehicle> allowedVehicleInMemoryRepository = new InMemoryRepository<>();
        InMemoryRepository<Ticket> ticketInMemoryRepository = new InMemoryRepository<>();
        InMemoryRepository<Vehicle> vehicleInMemoryRepository = new InMemoryRepository<>();

        DataGenerator dataGenerator = new DataGenerator(parkingLotInMemoryRepository,parkingFloorInMemoryRepository,
                parkingSpotInMemoryRepository,gateInMemoryRepository,operatorInMemoryRepository,
                allowedVehicleInMemoryRepository);

        dataGenerator.generateData();

        VehicleService vehicleService = new VehicleService(vehicleInMemoryRepository);
        TicketService ticketService = new TicketService(operatorInMemoryRepository,
                vehicleService,parkingSpotInMemoryRepository,ticketInMemoryRepository);

        TicketController ticketController = new TicketController(ticketService);

        GenerateTicketRequestDTO requestDTO =
                new GenerateTicketRequestDTO(12L,"MH-46-34523","Shikha",
                        "9867543423", VehicleType.BIKE);
        GenerateTicketResponseDTO responseDTO = ticketController.generateTicket(requestDTO);
        System.out.println(responseDTO.getMessage()+" | Ticket Id : "+responseDTO.getTicketNumber()
                +" | Parking Spot : "+responseDTO.getParkingSpot().getParkingSpotNumber());



        requestDTO = new GenerateTicketRequestDTO(12L,"MH-46-04523","Shikhar",
                        "9867543423", VehicleType.BIKE);
        responseDTO = ticketController.generateTicket(requestDTO);
        System.out.println(responseDTO.getMessage()+" | Ticket Id : "+responseDTO.getTicketNumber()
                +" | Parking Spot : "+responseDTO.getParkingSpot().getParkingSpotNumber());


        requestDTO = new GenerateTicketRequestDTO(12L,"MH-46-04573","Simran",
                "9867543423", VehicleType.BIKE);
        responseDTO = ticketController.generateTicket(requestDTO);
        System.out.println(responseDTO.getMessage()+" | Ticket Id : "+responseDTO.getTicketNumber()
                +" | Parking Spot : "+responseDTO.getParkingSpot().getParkingSpotNumber());


        requestDTO = new GenerateTicketRequestDTO(12L,"MH-46-04573","Sudhanshu",
                "9867543423", VehicleType.BIKE);
        responseDTO = ticketController.generateTicket(requestDTO);
        System.out.println(responseDTO.getMessage()+" | Ticket Id : "+responseDTO.getTicketNumber()
                +" | Parking Spot : "+responseDTO.getParkingSpot().getParkingSpotNumber());


        requestDTO = new GenerateTicketRequestDTO(12L,"MH-46-04573","Shekhar",
                "9867543423", VehicleType.BIKE);
        responseDTO = ticketController.generateTicket(requestDTO);
        System.out.println(responseDTO.getMessage()+" | Ticket Id : "+responseDTO.getTicketNumber()
                +" | Parking Spot : "+responseDTO.getParkingSpot().getParkingSpotNumber());


        requestDTO = new GenerateTicketRequestDTO(12L,"MH-46-04566","Sridhar",
                "9867543400", VehicleType.BIKE);
        responseDTO = ticketController.generateTicket(requestDTO);
        System.out.println(responseDTO.getMessage()+" | Ticket Id : "+responseDTO.getTicketNumber());



    }
}
