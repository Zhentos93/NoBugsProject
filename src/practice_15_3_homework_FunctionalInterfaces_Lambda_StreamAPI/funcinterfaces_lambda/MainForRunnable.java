/*Задача: Создайте анонимный класс, реализующий интерфейс Runnable, который выводит
сообщение "Hello from anonymous class!".*/

package practice_15_3_homework_FunctionalInterfaces_Lambda_StreamAPI.funcinterfaces_lambda;

import java.lang.Runnable;

public class MainForRunnable {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("Hello from anonymous class!");
        r.run();
    }
}
