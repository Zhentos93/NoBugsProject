package practice_15_1_lecture_Multithreading.Single_Multi_Thread;

public class SingleThreadExample {
    public static void main(String[] args) {
        System.out.println("Задача 1: Приготовление кофе");
        try {
            Thread.sleep(2000); // ждём 2 секунды
        } catch (InterruptedException e) {}

        System.out.println("Задача 2: Запуск браузера");
        try {
            Thread.sleep(2000); // ждём 2 секунды
        } catch (InterruptedException e) {}

        System.out.println("Задача 3: Открытие файла");
        try {
            Thread.sleep(2000); // ждём 2 секунды
        } catch (InterruptedException e) {}

        System.out.println("Все задачи выполнены!");
    }
}
