package controller;

import dtos.IssueTicketRequestDTO;
import dtos.IssueTicketResponseDTO;
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

    public IssueTicketResponseDTO issueTicket(IssueTicketRequestDTO requestDTO)
    {
        IssueTicketResponseDTO issueTicketResponseDTO = new IssueTicketResponseDTO();

        try
        {
            //Input Validation on the request DTO
            Ticket ticket = ticketService.generateTicket(requestDTO.getOperatorId(),
                    requestDTO.getRegistrationNumber(),requestDTO.getOwnerName(),
                    requestDTO.getOwnerContactNumber(),requestDTO.getVehicleType());

            issueTicketResponseDTO.setTicketNumber(ticket.getTicketNumber());
            issueTicketResponseDTO.setTicketId(ticket.getId());
            issueTicketResponseDTO.setEntry_time(ticket.getEntry_time());
            issueTicketResponseDTO.setParkingSpot(ticket.getParkingSpot());
            issueTicketResponseDTO.setMessage("Ticket Issued Successfully...");
            issueTicketResponseDTO.setResrponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e)
        {
            System.out.println("Error in generating ticket : "+e.getMessage());
            //e.printStackTrace();
            issueTicketResponseDTO.setResrponseStatus(ResponseStatus.FAILURE);
            issueTicketResponseDTO.setMessage(e.getMessage());
        }
        return issueTicketResponseDTO;
    }

    //Long operatorId, String registrationNumber, String ownerName,String ownerContactNumber,AllowedVehicleType vehicleType


}
