package practice_15_1_lecture_Multithreading;

class RestaurantStatus {
    private volatile boolean open = true;

    public void closeRestaurant() {
        open = false;
        System.out.println("Ресторан закрыт!");
    }

    public void work(String waiter) {
        while (open) {
            System.out.println(waiter + " обслуживает клиентов...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(waiter + " ушёл домой.");
    }
}

public class VolatileExample {
    public static void main(String[] args) throws InterruptedException {
        RestaurantStatus status = new RestaurantStatus();

        Thread waiter1 = new Thread(() -> status.work("Официант 1"));
        waiter1.start();

        Thread.sleep(2000);
        status.closeRestaurant(); // Закрываем ресторан
    }
}
