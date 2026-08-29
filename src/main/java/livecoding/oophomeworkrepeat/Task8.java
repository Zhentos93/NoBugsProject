package livecoding.oophomeworkrepeat;

import java.util.ArrayList;
import java.util.List;

abstract class PaymentMethod {
    private String owner;

    public PaymentMethod(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public abstract void pay(double amount);
}

class BankCard extends PaymentMethod {
    public BankCard(String owner) {
        super(owner);
    }

    @Override
    public void pay(double amount) {
        System.out.println("Карта " + getOwner() + " оплатила " + amount);
    }
}

class PayPal extends PaymentMethod {
    public PayPal(String owner) {
        super(owner);
    }

    @Override
    public void pay(double amount) {
        System.out.println("PayPal " + getOwner() + " оплатил " + amount);
    }
}

class CryptoWallet extends PaymentMethod {
    public CryptoWallet(String owner) {
        super(owner);
    }

    @Override
    public void pay(double amount) {
        System.out.println("Криптокошелек " + getOwner() + " отправил " + amount);
    }
}

class PaymentsSystem {
    private List<PaymentMethod> payments;
    private double amount;

    public PaymentsSystem(List<PaymentMethod> payments, double amount) {
        this.payments = payments;
        this.amount = amount;
    }

    public void payAll() {
        for (PaymentMethod paymentMethod : payments) {
            paymentMethod.pay(amount);
        }
    }
}

public class Task8 {
    public static void main(String[] args) {
        List<PaymentMethod> payments = new ArrayList<>();

        payments.add(new BankCard("Alex"));
        payments.add(new PayPal("Maria"));
        payments.add(new CryptoWallet("Ivan"));

        PaymentsSystem paymentsSystem = new PaymentsSystem(payments, 1200);

        paymentsSystem.payAll();
    }
}
