package practice_15_1_lecture_Multithreading.Single_Multi_Thread;

public class MultiThreadExampleNoSleep {
    public static void main(String[] args) {
        Thread coffeeTask = new Thread(() -> {
            System.out.println("Приготовление кофе");
            System.out.println("Кофе готов!");
        });

        Thread browserTask = new Thread(() -> {
            System.out.println("Запуск браузера");
            System.out.println("Браузер открыт!");
        });

        Thread fileTask = new Thread(() -> {
            System.out.println("Открытие файла");
            System.out.println("Файл открыт!");
        });

        coffeeTask.start();
        browserTask.start();
        fileTask.start();
    }
}
