package practice_15_1_lecture_Multithreading.Single_Multi_Thread;

// Мы обрабатываем через try-catch, т.к. есть метод sleep()

public class MultiThreadExampleWithSleep {
    public static void main(String[] args) {
        Thread coffeeTask = new Thread(() -> {
            System.out.println("Приготовление кофе");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Кофе готов!");
        });

        Thread browserTask = new Thread(() -> {
            System.out.println("Запуск браузера");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Браузер открыт!");
        });

        Thread fileTask = new Thread(() -> {
            System.out.println("Открытие файла");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Файл открыт!");
        });

        coffeeTask.start();
        browserTask.start();
        fileTask.start();
    }
}
