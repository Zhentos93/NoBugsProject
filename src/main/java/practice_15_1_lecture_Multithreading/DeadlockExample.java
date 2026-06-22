package practice_15_1_lecture_Multithreading;

class Resource {
    private final String name;

    public Resource(String name) {
        this.name = name;
    }

    public synchronized void use(Resource other) {
        System.out.println(Thread.currentThread().getName() + " держит " + name);
        try { Thread.sleep(100); } catch (InterruptedException ignored) {}
        synchronized (other) {
            System.out.println(Thread.currentThread().getName() + " использует " + other.name);
        }
    }
}

public class DeadlockExample {
    public static void main(String[] args) {
        Resource tray = new Resource("Поднос");
        Resource bill = new Resource("Счёт");

        Thread waiter1 = new Thread(() -> tray.use(bill), "Официант 1");
        Thread waiter2 = new Thread(() -> bill.use(tray), "Официант 2"); // это deadlock
//        Thread waiter2 = new Thread(() -> bill.use(bill), "Официант 2"); // это решение, как deadlock исключить

        waiter1.start();
        waiter2.start();
    }
}
