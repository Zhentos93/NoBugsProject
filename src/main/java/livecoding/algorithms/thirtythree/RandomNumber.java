package livecoding.algorithms.thirtythree;

/*
 * Задача №33. Генерация случайного числа
 *
 * Сгенерировать случайное число от 1 до 10.
 */

// Ошибки
/*
 * Задача №33 — генерация случайного числа:
 *
 * 1. Рабочий метод остался незавершённым.
 *
 * 2. Не был добавлен импорт класса Random:
 *    import java.util.Random;
 *
 * 3. Не был использован метод nextInt().
 */

import java.util.Random;

public class RandomNumber {
    public RandomNumber() {
    }

    public int random1to10() {
        return new Random().nextInt(10) + 1;
    }

    public static void main(String[] args) {
        RandomNumber randomNumber = new RandomNumber();

        System.out.println(randomNumber.random1to10());
    }
}
