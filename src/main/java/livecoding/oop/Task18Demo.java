package livecoding.oop;

enum OrderStatus {
    CREATED,
    PAID,
    SHIPPED,
    DELIVERED,
    CANCELLED
}

class Order1 {
    private final String id;
    private OrderStatus status;

    public Order1(String id) {
        this.id = id;
        this.status = OrderStatus.CREATED; // Начальное состояние любого заказа
        System.out.println("Order " + id + " создан");
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void requireStatus(OrderStatus expected, String message) {
        if (status != expected) {
            // Запрещённый переход не должен менять состояние заказа
            throw new IllegalStateException(message);
        }
    }

    public void pay() {
        requireStatus(OrderStatus.CREATED, "оплатить можно только созданный заказ");
        status = OrderStatus.PAID; // CREATED → PAID
        System.out.println("Order " + id + " оплачен");
    }

    public void ship() {
        requireStatus(OrderStatus.PAID, "заказ должен быть оплачен");
        status = OrderStatus.SHIPPED; // PAID → SHIPPED
        System.out.println("Order " + id + " отправлен");
    }

    public void deliver() {
        requireStatus(OrderStatus.SHIPPED, "заказ должен быть отправлен");
        status = OrderStatus.DELIVERED; // SHIPPED → DELIVERED
        System.out.println("Order " + id + " доставлен");
    }

    public void cancel() {
        if (status != OrderStatus.CREATED && status != OrderStatus.PAID) {
            throw new IllegalStateException(
                    "Отменить можно только созданный или оплаченный заказ");
        }
        status = OrderStatus.CANCELLED; // CREATED/PAID → CANCELLED
        System.out.println("Order " + id + " отменён");
    }
}

public class Task18Demo {
    public static void main(String[] args) {
        Order1 order = new Order1("1001");
        order.pay();
        order.ship();
        order.deliver();

        try {
            new Order1("1002").ship();
        } catch (IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
