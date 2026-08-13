package livecoding.algorithms.fourtyseven;

/*
 * Задача №47. Подсчёт слов
 *
 * Подсчитать количество слов в строке.
 */

import java.util.Arrays;

public class StreamWordCounter {

    public StreamWordCounter() {
    }

    public int countWords(String s) {
        return (int) Arrays.stream(s.trim().split("\\s+"))
                .filter(word -> !word.isEmpty())
                .count();
    }

    public static void main(String[] args) {
        StreamWordCounter counter = new StreamWordCounter();

        System.out.println(
                counter.countWords("Java is fun")
        );
        System.out.println(
                counter.countWords("  Java   is fun  ")
        );
        System.out.println(
                counter.countWords("one")
        );
        System.out.println(
                counter.countWords("   ")
        );
        System.out.println(
                counter.countWords("")
        );
    }
}
