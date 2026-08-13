package livecoding.algorithms.thirtythree;

/*
 * Задача №33. Генерация случайного числа
 *
 * Сгенерировать случайное число от 1 до 10.
 */

import java.util.Random;

public class RandomNumberGenerator {

    public RandomNumberGenerator() {
    }

    int random1to10() {
        return new Random().nextInt(10) + 1;
    }

    public static void main(String[] args) {
        RandomNumberGenerator generator =
                new RandomNumberGenerator();

        System.out.println(generator.random1to10());
        System.out.println(generator.random1to10());
        System.out.println(generator.random1to10());
        System.out.println(generator.random1to10());
        System.out.println(generator.random1to10());
    }
}
