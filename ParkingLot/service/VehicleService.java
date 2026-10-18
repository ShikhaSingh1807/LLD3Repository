package service;

import models.Vehicle;
import models.enums.VehicleType;
import repository.InMemoryRepository;

import java.util.List;

public class VehicleService
{
    private InMemoryRepository<Vehicle> vehicleInMemoryRepository;

    public VehicleService(InMemoryRepository<Vehicle> vehicleInMemoryRepository) {
        this.vehicleInMemoryRepository = vehicleInMemoryRepository;
    }

    public Vehicle findOrCreate(String registrationNumber, String ownerName, String ownerContactNumber,
                                VehicleType vehicleType)
    {
        List<Vehicle>  allVehicles = vehicleInMemoryRepository.findAll();

        for(Vehicle vehicle : allVehicles)
        {
            if(vehicle.getRegistrationNumber().equals(registrationNumber))
                return vehicle;
        }

        Vehicle vehicle = new Vehicle(ownerName,ownerContactNumber,registrationNumber,vehicleType);
        return  vehicleInMemoryRepository.save(vehicle);
    }
}
