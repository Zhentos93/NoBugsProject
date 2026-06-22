/*Задача: Напишите лямбду, которая проверяет, является ли число чётным.*/

package practice_14_3_homework_FunctionalInterfaces_Lambda_StreamAPI.funcinterfaces_lambda;

public class MainForPredicate {
    public static void main(String[] args) {
        Predicate<Integer> parity = x -> x % 2 == 0;
        System.out.println(parity.test(4));
        System.out.println(parity.test(3));
    }
}
