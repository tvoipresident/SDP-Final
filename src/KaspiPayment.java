public class KaspiPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid ₸" + amount + " using Kaspi.");
    }
}
