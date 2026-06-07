/*Задача: Создайте лямбду, которая принимает строку и возвращает её длину.*/

package practice_14_3_homework_FunctionalInterfaces_Lambda_StreamAPI.funcinterfaces_lambda;

public class MainForFunction {
    public static void main(String[] args) {
        Function<String, Integer> getLength = String::length;
        System.out.println(getLength.apply("Hello, world!"));
    }
}
