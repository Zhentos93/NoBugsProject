package livecoding.oophomework;

import java.util.ArrayList;
import java.util.List;

class Order {
    private String id;
    private List<OrderItem> orders = new ArrayList<>();

    public Order(String id) {
        this.id = id;
    }

    public void addItem(OrderItem orderItem) {
        orders.add(orderItem);
    }

    public double totalItems() {
        double total = 0;

        for (OrderItem orderItem : orders) {
            total += orderItem.itemTotal();
        }

        return total;
    }
}

class OrderItem {
    private String name;
    private double price;
    private int quantity;

    public OrderItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double itemTotal() {
        return price * quantity;
    }
}

public class Task11 {
    public static void main(String[] args) {
        Order order = new Order("1001");

        order.addItem(new OrderItem("Ноутбук", 1000, 1));
        order.addItem(new OrderItem("Мышь", 50, 2));
        order.addItem(new OrderItem("Клавиатура", 120, 1));

        System.out.println("Стоимость заказа: " + order.totalItems());
    }
}
