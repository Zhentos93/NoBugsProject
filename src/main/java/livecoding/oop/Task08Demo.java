package livecoding.oop;

import java.util.List;

// Абстрактный: владелец общий, но универсального способа оплаты нет
abstract class PaymentMethod {
    private final String owner;

    public PaymentMethod(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    // Абстрактный: каждый способ самостоятельно выполняет оплату переданной суммы
    public abstract void pay(double amount);
}

class CardPayment extends PaymentMethod {
    public CardPayment(String owner) {
        super(owner);
    }

    @Override
    public void pay(double amount) {
        System.out.println("Карта " + getOwner() + " оплатила " + amount);
    }
}

class PayPalPayment extends PaymentMethod {
    public PayPalPayment(String owner) {
        super(owner);
    }

    @Override
    public void pay(double amount) {
        System.out.println("PayPal " + getOwner() + " оплатил " + amount);
    }
}

class CryptoPayment extends PaymentMethod {
    public CryptoPayment(String owner) {
        super(owner);
    }

    @Override
    public void pay(double amount) {
        System.out.println("Криптокошелек " + getOwner() + " отправил " + amount);
    }
}

class PaymentService {
    public void payAll(List<PaymentMethod> payments, double amount) {
        for (PaymentMethod payment : payments) {
            payment.pay(amount); // Один amount передаём каждой реализации pay()
        }
    }
}

public class Task08Demo {
    public static void main(String[] args) {
        List<PaymentMethod> payments = List.of(
                new CardPayment("Alex"),
                new PayPalPayment("Maria"),
                new CryptoPayment("Ivan")
        );

        PaymentService paymentService = new PaymentService();
        paymentService.payAll(payments, 1200);
    }
}