package livecoding.algorithms.twentythree;

/*
 * Задача №23. Проверка чётности числа
 *
 * Вернуть true, если число чётное.
 */

// Ошибок при решении нет

public class EvenNumber {
    public EvenNumber() {
    }

    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static void main(String[] args) {
        EvenNumber evenNumber = new EvenNumber();

        System.out.println(evenNumber.isEven(8));
        System.out.println(evenNumber.isEven(7));
        System.out.println(evenNumber.isEven(0));
        System.out.println(evenNumber.isEven(-4));
        System.out.println(evenNumber.isEven(-3));
    }
}
