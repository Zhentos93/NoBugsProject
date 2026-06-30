package practice_17_2_practice_Trubleshuting.mistake;

import java.util.ArrayList;
import java.util.List;

public class MultiThreadedList {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();

        Thread thread1 = new Thread(() -> addNumbers(list));
        Thread thread2 = new Thread(() -> addNumbers(list));
        Thread thread3 = new Thread(() -> addNumbers(list));

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Размер списка: " + list.size());
    }

    private synchronized static void addNumbers(List<Integer> list) {
        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }
    }
}

// добавили synchronized для метода addNumbers