package practice_15_1_lecture_Multithreading;

class Waiter extends Thread {
    private String name;

    public Waiter(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(name + " обслуживает клиента...");
        try {
            Thread.sleep(3000); // Официант занят 3 секунды
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " закончил обслуживание!");
    }
}

public class Restaurant {
    public static void main(String[] args) {
        Waiter waiter1 = new Waiter("Официант 1");
        waiter1.start(); // Запускаем поток
    }
}