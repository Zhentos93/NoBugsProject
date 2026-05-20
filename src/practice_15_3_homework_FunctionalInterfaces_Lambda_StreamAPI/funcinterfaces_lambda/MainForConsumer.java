/*Задача: Напишите лямбду, которая принимает строку и печатает её в консоль.*/

package practice_15_3_homework_FunctionalInterfaces_Lambda_StreamAPI.funcinterfaces_lambda;

public class MainForConsumer {
    public static void main(String[] args) {
        Consumer<String> printer = message -> System.out.println("Сообщение: " + message);
        printer.accept("Привет, мир!");
    }
}
