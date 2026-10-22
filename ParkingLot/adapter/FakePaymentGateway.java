package adapter;

public class FakePaymentGateway implements PaymentGateWay
{
    @Override
    public boolean processOnlinePayment(Double amount, String referenceNumber)
    {
        // MVP note: this is a stand-in for a real third-party
        // gateway call. A real version would make an actual
        // network call here.
        System.out.println("Simulating online payment of " + amount
                + " with reference " + referenceNumber);
        return true;
    }
}
