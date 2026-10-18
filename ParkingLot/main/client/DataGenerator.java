package main.client;

import models.*;
import models.enums.*;
import repository.InMemoryRepository;

import java.util.List;

public class DataGenerator
{
    private InMemoryRepository<ParkingLot> parkingLotInMemoryRepository;
    private InMemoryRepository<ParkingFloor> parkingFloorInMemoryRepository;
    private InMemoryRepository<ParkingSpot> parkingSpotInMemoryRepository;
    private InMemoryRepository<Gate> gateInMemoryRepository;
    private InMemoryRepository<Operator> operatorInMemoryRepository;
    private InMemoryRepository<AllowedVehicle> allowedVehicleInMemoryRepository;

    public DataGenerator(InMemoryRepository<ParkingLot> parkingLotInMemoryRepository,
                         InMemoryRepository<ParkingFloor> parkingFloorInMemoryRepository,
                         InMemoryRepository<ParkingSpot> parkingSpotInMemoryRepository,
                         InMemoryRepository<Gate> gateInMemoryRepository,
                         InMemoryRepository<Operator> operatorInMemoryRepository,
                         InMemoryRepository<AllowedVehicle> allowedVehicleInMemoryRepository)
    {
        this.parkingLotInMemoryRepository = parkingLotInMemoryRepository;
        this.parkingFloorInMemoryRepository = parkingFloorInMemoryRepository;
        this.parkingSpotInMemoryRepository = parkingSpotInMemoryRepository;
        this.gateInMemoryRepository = gateInMemoryRepository;
        this.operatorInMemoryRepository = operatorInMemoryRepository;
        this.allowedVehicleInMemoryRepository = allowedVehicleInMemoryRepository;
    }

    public void generateData()
    {
        //Things to populate or create before running program

        //1 Parking Lot
        ParkingLot parkingLot = new ParkingLot("Pheonix", PricingStrategyTypes.HOURLY, SpotAllocationStrategyType.RANDOM);

        //2 Parking Floors
        ParkingFloor parkingFloor1 = new ParkingFloor(1);
        ParkingFloor parkingFloor2 = new ParkingFloor(2);


        //4 Parking Slots :- 2 each for BIKE and CAR
        ParkingSpot parkingSpot1A =  new ParkingSpot("1A", VehicleType.BIKE);
        ParkingSpot parkingSpot1B = new ParkingSpot("1B",VehicleType.BIKE);
        ParkingSpot parkingSpot1C = new ParkingSpot("1C",VehicleType.CAR);
        ParkingSpot parkingSpot1D = new ParkingSpot("1D",VehicleType.CAR);
        ParkingSpot parkingSpot1E = new ParkingSpot("1E",VehicleType.CAR);

        ParkingSpot parkingSpot2A =  new ParkingSpot("2A", VehicleType.BIKE);
        ParkingSpot parkingSpot2B = new ParkingSpot("2B",VehicleType.BIKE);
        ParkingSpot parkingSpot2C = new ParkingSpot("2C",VehicleType.TRUCK);
        ParkingSpot parkingSpot2D = new ParkingSpot("2D",VehicleType.BIKE);
        ParkingSpot parkingSpot2E = new ParkingSpot("2E",VehicleType.CAR);
        ParkingSpot parkingSpot2F = new ParkingSpot("2F",VehicleType.CAR);

        parkingSpotInMemoryRepository.save(parkingSpot1A);
        parkingSpotInMemoryRepository.save(parkingSpot1B);
        parkingSpotInMemoryRepository.save(parkingSpot1C);
        parkingSpotInMemoryRepository.save(parkingSpot1D);
        parkingSpotInMemoryRepository.save(parkingSpot1E);
        parkingSpotInMemoryRepository.save(parkingSpot2A);
        parkingSpotInMemoryRepository.save(parkingSpot2B);
        parkingSpotInMemoryRepository.save(parkingSpot2C);
        parkingSpotInMemoryRepository.save(parkingSpot2D);
        parkingSpotInMemoryRepository.save(parkingSpot2E);
        parkingSpotInMemoryRepository.save(parkingSpot2F);

        //2 Gates
        Gate entryGate = new Gate(GateStatus.OPEN,1, GateType.ENTRY);

        Gate exitGate = new Gate(GateStatus.OPEN,2,GateType.EXIT);

        //2 Operators

        Operator operator1 = new Operator("OpName1","123456","908876546783");
        operator1.setGate(entryGate);
        operatorInMemoryRepository.save(operator1);
        System.out.println("Operator 1 id : "+ operator1.getId());

        Operator operator2 = new Operator("OpName2","345677","763342565666");
        operator2.setGate(exitGate);
        operatorInMemoryRepository.save(operator2);
        System.out.println("Operator 2 id : "+ operator2.getId());


        // Some Allowed vehicles

        AllowedVehicle allowedVehicleParkingLotCar = new AllowedVehicle(VehicleType.CAR,5);
        AllowedVehicle allowedVehicleParkingLotBike = new AllowedVehicle(VehicleType.BIKE,5);
        AllowedVehicle allowedVehicleParkingLotTruck = new AllowedVehicle(VehicleType.TRUCK,1);

        AllowedVehicle allowedVehicleParkingFloor1Car = new AllowedVehicle(VehicleType.CAR,3);
        AllowedVehicle allowedVehicleParkingFloor1Bike = new AllowedVehicle(VehicleType.BIKE,2);
        AllowedVehicle allowedVehicleParkingFloor1Truck = new AllowedVehicle(VehicleType.TRUCK,0);

        AllowedVehicle allowedVehicleParkingFloor2Car = new AllowedVehicle(VehicleType.CAR,2);
        AllowedVehicle allowedVehicleParkingFloor2Bike = new AllowedVehicle(VehicleType.BIKE,3);
        AllowedVehicle allowedVehicleParkingFloor2Truck = new AllowedVehicle(VehicleType.TRUCK,1);

        List<AllowedVehicle> allAllowedVehicles = List.of(allowedVehicleParkingLotCar,allowedVehicleParkingLotBike
                ,allowedVehicleParkingLotTruck,allowedVehicleParkingFloor1Car,allowedVehicleParkingFloor1Bike,
                allowedVehicleParkingFloor1Truck,allowedVehicleParkingFloor2Car,allowedVehicleParkingFloor2Bike,
                allowedVehicleParkingFloor2Truck);

        allAllowedVehicles.forEach(av-> allowedVehicleInMemoryRepository.save(av));


        parkingLot.setAllowed_vehicles(List.of(allowedVehicleParkingLotBike,allowedVehicleParkingLotCar,
                allowedVehicleParkingLotTruck));
        parkingLot.setEntry_gates(List.of(entryGate));
        parkingLot.setExit_gates(List.of(exitGate));
        parkingLot.setParkingFloors(List.of(parkingFloor1,parkingFloor2));
        parkingLotInMemoryRepository.save(parkingLot);



        parkingFloor1.setAllowedVehicles(List.of(allowedVehicleParkingFloor1Bike,allowedVehicleParkingFloor1Car,
                allowedVehicleParkingFloor1Truck));
        parkingFloor1.setParkingSpots(List.of(parkingSpot1A,parkingSpot1B,parkingSpot1C,parkingSpot1D,parkingSpot1E));

        parkingFloorInMemoryRepository.save(parkingFloor1);
        parkingFloor2.setAllowedVehicles(List.of(allowedVehicleParkingFloor2Bike,allowedVehicleParkingFloor2Car,
                allowedVehicleParkingFloor2Truck));
        parkingFloor2.setParkingSpots(List.of(parkingSpot2A,parkingSpot2B,parkingSpot2C,parkingSpot2D,parkingSpot2E,
                parkingSpot2F));
        parkingFloorInMemoryRepository.save(parkingFloor2);



        entryGate.setOperator(operator1);
        entryGate.setParkingLot(parkingLot);

        gateInMemoryRepository.save(entryGate);

        exitGate.setOperator(operator2);
        exitGate.setParkingLot(parkingLot);

        gateInMemoryRepository.save(exitGate);


    }
}
