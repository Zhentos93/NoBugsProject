package сode_reading.JavaCodeReading;

class Counter {
    int value;

    synchronized void increment() {
        value++;
    }
}

public class A37 {
    public static void main(String[] args)
            throws InterruptedException {

        Counter counter = new Counter();

        Thread first = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread second = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        first.start();
        second.start();

        first.join();
        second.join();

        System.out.println(counter.value);
    }
}
