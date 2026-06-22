/*Задача: реализовать класс StatusChecker, который содержит специальную переменную active и потоки могут использовать
 * эту переменную для проверки, нужно ли им продолжать выполнение, если другой поток устанавливает active = false,
 * все потоки должны корректно завершить управление*/

package practice_15_2_practice_Multithreading.volatilekeyword;

public class StatusChecker implements Runnable {
    // если active = false, все потоки должны завершить исполнение
    private volatile boolean active = true;

    @Override
    public void run() {
        while (active) {
            System.out.println(Thread.currentThread().getName() + " is running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        this.active = false;
    }
}
