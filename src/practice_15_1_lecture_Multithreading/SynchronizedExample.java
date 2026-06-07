package practice_15_1_lecture_Multithreading;

class OrderBook {
    private int orderNumber = 0;

    public synchronized void takeOrder(String waiter) {
        orderNumber++;
        System.out.println(waiter + " записал заказ №" + orderNumber);
    }
}

public class SynchronizedExample {
    public static void main(String[] args) {
        OrderBook book = new OrderBook();

        Runnable task = () -> {
            for (int i = 0; i < 3; i++) {
                book.takeOrder(Thread.currentThread().getName());
            }
        };

        Thread waiter1 = new Thread(task, "Официант 1");
        Thread waiter2 = new Thread(task, "Официант 2");

        waiter1.start();
        waiter2.start();
    }
}
