package livecoding.oophomeworkrepeat;

enum OrderStatus {
    CREATED,
    PAID,
    SHIPPED,
    DELIVERED,
    CANCELLED
}

class Item {
    private String id;
    private OrderStatus orderStatus;

    public Item(String id) {
        this.id = id;
        this.orderStatus = OrderStatus.CREATED;
        System.out.println("Заказ создан");
    }

    public String getId() {
        return id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void checkStatus(OrderStatus expected, String message) {
        if (expected != orderStatus) {
            throw new IllegalStateException(message);
        }
    }

    public void payItem() {
        checkStatus(OrderStatus.CREATED, "Заказ должен быть создан");
        orderStatus = OrderStatus.PAID;
        System.out.println("Заказ оплачен");
    }

    public void sendItem() {
        checkStatus(OrderStatus.PAID, "Заказ должен быть оплачен");
        orderStatus = OrderStatus.SHIPPED;
        System.out.println("Заказ отправлен");
    }

    public void deliverItem() {
        checkStatus(OrderStatus.SHIPPED, "Заказ должен быть отправлен");
        orderStatus = OrderStatus.DELIVERED;
        System.out.println("Заказ доставлен");
    }

    public void cancelItem() {
        if (orderStatus == OrderStatus.CREATED || orderStatus == OrderStatus.PAID) {
            orderStatus = OrderStatus.CANCELLED;
            System.out.println("Заказ отменен");
        } else {
            throw new IllegalStateException("Заказ должен быть в статусе Создан или Оплачен");
        }
    }
}

public class Task18 {
    public static void main(String[] args) {
        Item item = new Item("Заказ 1");

        item.payItem();
        item.sendItem();
        item.deliverItem();

        item.cancelItem();
    }
}
