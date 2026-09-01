package livecoding.oophomeworkrepeat;

import java.util.ArrayList;
import java.util.List;

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

    public double calculate() {
        return price * quantity;
    }
}

class Order {
    private String id;
    private List<OrderItem> items = new ArrayList<>();

    public Order(String id) {
        this.id = id;
    }

    public void addItem(OrderItem orderItem) {
        items.add(orderItem);
    }

    public double calculateTotal() {
        double total = 0;

        for (OrderItem orderItem : items) {
            total += orderItem.calculate();
        }

        return total;
    }
}

public class Task11 {
    public static void main(String[] args) {
        Order order = new Order("1001");

        order.addItem(new OrderItem("Ноутбук", 1000, 1));
        order.addItem(new OrderItem("Мышь", 50, 2));
        order.addItem(new OrderItem("Клавиатура", 120, 1));

        System.out.println("Стоимость заказа: " + order.calculateTotal());
    }
}
