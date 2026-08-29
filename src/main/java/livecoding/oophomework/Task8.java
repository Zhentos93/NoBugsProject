package livecoding.oophomework;

import java.util.ArrayList;
import java.util.List;

abstract class Payment {
    private String owner;

    public Payment(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public abstract void pay(double amount);
}

class BankCard extends Payment {
    public BankCard(String owner) {
        super(owner);
    }

    @Override
    public void pay(double amount) {
        System.out.println("Карта " + getOwner() + " оплатила " + amount);
    }
}

class PayPal extends Payment {
    public PayPal(String owner) {
        super(owner);
    }

    @Override
    public void pay(double amount) {
        System.out.println("PayPal " + getOwner() + " оплатил " + amount);
    }
}

class CryptoWallet extends Payment {
    public CryptoWallet(String owner) {
        super(owner);
    }

    @Override
    public void pay(double amount) {
        System.out.println("Криптокошелек " + getOwner() + " отправил " + amount);
    }
}

class PaymentSystem {
    private List<Payment> payments;
    private double amount;

    public PaymentSystem(List<Payment> payments, double amount) {
        this.payments = payments;
        this.amount = amount;
    }

    public void payAll() {
        for (Payment payment : payments) {
            payment.pay(amount);
        }
    }
}

public class Task8 {
    public static void main(String[] args) {
        List<Payment> payments = new ArrayList<>();

        payments.add(new BankCard("Alex"));
        payments.add(new PayPal("Maria"));
        payments.add(new CryptoWallet("Ivan"));

        PaymentSystem paymentSystem = new PaymentSystem(payments, 1200);

        paymentSystem.payAll();
    }
}

