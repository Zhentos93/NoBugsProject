package livecoding.oophomework;

enum OrderStatus {
    CREATED,
    PAID,
    SHIPPED,
    DELIVERED,
    CANCELLED;
}

class Order1 {
    private String id;
    private OrderStatus orderStatus;

    public Order1(String id) {
        this.id = id;
        this.orderStatus = OrderStatus.CREATED;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void checkStatus(OrderStatus expected, String message) {
        if (orderStatus != expected) {
            throw new IllegalStateException(message);
        }
    }

    public void payOrder() {
        checkStatus(OrderStatus.CREATED, "Заказ должен быть создан");
        orderStatus = OrderStatus.PAID;
        System.out.println("Заказ " + id + " оплачен");
    }

    public void sendOrder() {
        checkStatus(OrderStatus.PAID, "Заказ должен быть оплачен");
        orderStatus = OrderStatus.SHIPPED;
        System.out.println("Заказ " + id + " отправлен");
    }

    public void deliverOrder() {
        checkStatus(OrderStatus.SHIPPED, "Заказ должен быть отправлен");
        orderStatus = OrderStatus.DELIVERED;
        System.out.println("Заказ " + id + " доставлен");
    }

    public void cancelOrder() {
        if (orderStatus == OrderStatus.CREATED || orderStatus == OrderStatus.PAID) {
            orderStatus = OrderStatus.CANCELLED;
            System.out.println("Заказ " + id + " отменен");
        } else {
            throw new IllegalStateException("Отменить можно только созданный или оплаченный заказ");
        }
    }
}

public class Task18 {
    public static void main(String[] args) {
        Order1 order1 = new Order1("123");

        order1.payOrder();
        order1.sendOrder();
        System.out.println(order1.getOrderStatus());
        order1.deliverOrder();

        try {
            order1.cancelOrder();
        } catch (IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
