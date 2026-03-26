package practice_10.restaurant_manager;

/*
Пример 2. Система управления заказами в ресторане. Заказы обрабатываются в порядке поступления заказов.
В любой момент можно удалить любой заказ.
*/

import java.util.LinkedList;

public class RestaurantManager {
    private LinkedList<String> orders;

    public RestaurantManager() {
        this.orders = new LinkedList<>();
    }

    // добавлять заказ в конец очереди
    public void addNewOrder(String order) {
        orders.addLast(order);
    }

    // обрабатывать заказ из начала очереди
    public String getNextOrderForProcess() {
        return orders.poll();
    }

    // удалять заказ в любом месте очереди
    public void deleteOrder(String order) {
        orders.remove(order);
    }

    public void printOrders() {
        System.out.println("Все заказы:");
        orders.forEach(System.out::println);
        System.out.println();
    }
}
