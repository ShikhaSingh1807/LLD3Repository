package dtos;

import models.enums.PaymentMode;

public class MakePaymentRequestDTO
{
    private Long billId;
    private PaymentMode paymentMode;
    private Double amountToBePaid; // This is different from billAmount as this tells how much we are paying in this part

    public MakePaymentRequestDTO(Long billId, PaymentMode paymentMode, Double amountToBePaid) {
        this.billId = billId;
        this.paymentMode = paymentMode;
        this.amountToBePaid = amountToBePaid;
    }

    public Double getAmountToBePaid() {
        return amountToBePaid;
    }

    public void setAmountToBePaid(Double amountToBePaid) {
        this.amountToBePaid = amountToBePaid;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }
}
