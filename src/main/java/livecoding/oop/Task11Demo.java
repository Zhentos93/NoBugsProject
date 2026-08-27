package livecoding.oop;

import java.util.ArrayList;
import java.util.List;

class Order {
    private final String orderId;
    private final List<OrderItem> items = new ArrayList<>();

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getTotal(); // Складываем стоимости всех позиций
        }
        return total;
    }
}

class OrderItem {
    private final String name;
    private final double price;
    private final int quantity;

    public OrderItem(String name, double price, int quantity) {
        if (price < 0 || quantity <= 0) {
            throw new IllegalArgumentException("Цена неотрицательна, количество положительно");
        }
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotal() {
        return price * quantity; // Считаем стоимость одной позиции заказа
    }
}

public class Task11Demo {
    public static void main(String[] args) {
        Order order = new Order("1001");
        order.addItem(new OrderItem("Ноутбук", 1000, 1));
        order.addItem(new OrderItem("Мышь", 50, 2));
        order.addItem(new OrderItem("Клавиатура", 120, 1));

        System.out.println("Стоимость заказа: " + order.calculateTotal());
    }
}
