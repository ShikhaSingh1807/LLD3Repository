package controller;

import dtos.GenerateTicketRequestDTO;
import dtos.GenerateTicketResponseDTO;
import dtos.ResponseStatus;
import models.Ticket;
import service.TicketService;

public class TicketController
{
    private TicketService ticketService;

    public TicketController(TicketService ticketService)
    {
        this.ticketService = ticketService;
    }

    public GenerateTicketResponseDTO generateTicket(GenerateTicketRequestDTO requestDTO)
    {
        GenerateTicketResponseDTO generateTicketResponseDTO = new GenerateTicketResponseDTO();

        try
        {
            //Input Validation on the request DTO
            Ticket ticket = ticketService.generateTicket(requestDTO.getOperatorId(),
                    requestDTO.getRegistrationNumber(),requestDTO.getOwnerName(),
                    requestDTO.getOwnerContactNumber(),requestDTO.getVehicleType());

            generateTicketResponseDTO.setTicketNumber(ticket.getTicketNumber());
            generateTicketResponseDTO.setTicketId(ticket.getId());
            generateTicketResponseDTO.setEntry_time(ticket.getEntry_time());
            generateTicketResponseDTO.setParkingSpot(ticket.getParkingSpot());
            generateTicketResponseDTO.setMessage("Ticket Generated Successfully...");
            generateTicketResponseDTO.setResrponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e)
        {
            System.out.println("Error in generating ticket : "+e.getMessage());
            //e.printStackTrace();
            generateTicketResponseDTO.setResrponseStatus(ResponseStatus.FAILURE);
            generateTicketResponseDTO.setMessage(e.getMessage());
        }
        return generateTicketResponseDTO;
    }

    //Long operatorId, String registrationNumber, String ownerName,String ownerContactNumber,AllowedVehicleType vehicleType


}
