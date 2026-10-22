package controller;

import dtos.GenerateBillRequestDTO;
import dtos.GenerateBillResponseDTO;
import dtos.ResponseStatus;
import models.Bill;
import service.BillService;

public class BillController
{
    private BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    public GenerateBillResponseDTO generateBill(GenerateBillRequestDTO requestDTO)
    {
        GenerateBillResponseDTO responseDTO = new GenerateBillResponseDTO();

        try
        {
            Bill bill = billService.generateBill(requestDTO.getTicketId(), requestDTO.getOperatorId());
            responseDTO.setBillAmount(bill.getAmount());
            responseDTO.setBillNumber(bill.getBillNumber());
            responseDTO.setBillGeneratedDateTime(bill.getExit_time());
            responseDTO.setBillId(bill.getId());
            responseDTO.setMessage("Bill Generated Successfully...");
            responseDTO.setStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception ex)
        {
            System.out.println("Error in generating bill : "+ex.getMessage());
            responseDTO.setStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage(ex.getMessage());
            //ex.printStackTrace();
        }
        return responseDTO;
    }
}
