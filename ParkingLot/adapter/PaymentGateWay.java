package adapter;

public interface PaymentGateWay
{
    boolean processOnlinePayment (Double amount , String referenceNumber);
}