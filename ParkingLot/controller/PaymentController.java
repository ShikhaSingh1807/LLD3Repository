package controller;

import dtos.MakePaymentRequestDTO;
import dtos.MakePaymentResponseDTO;
import dtos.ResponseStatus;
import models.Payment;
import service.PaymentService;

public class PaymentController
{
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public MakePaymentResponseDTO makePayment(MakePaymentRequestDTO requestDTO)
    {
        MakePaymentResponseDTO responseDTO = new MakePaymentResponseDTO();

        try
        {
            Payment payment = paymentService.makePayment(requestDTO.getBillId(),requestDTO.getPaymentMode(),
                    requestDTO.getAmountToBePaid());
            responseDTO.setMessage(payment.getMessage());
            responseDTO.setStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception ex)
        {
            responseDTO.setStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage(ex.getMessage());
            ex.printStackTrace();
        }

        return responseDTO;
    }
}
