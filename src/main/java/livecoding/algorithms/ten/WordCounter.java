package livecoding.algorithms.ten;

/*
 * Задача №10. Подсчёт слов в строке
 *
 * Посчитать количество слов в строке.
 */

public class WordCounter {

    public WordCounter() {
    }

    int countWords(String s) {
        if (s == null || s.trim().isEmpty()) { // Проверяем отсутствие текста
            return 0;
        }

        return s.trim().split("\\s+").length;
    }

    public static void main(String[] args) {
        WordCounter counter = new WordCounter();

        System.out.println(counter.countWords("Java is fun"));
        System.out.println(counter.countWords("  Java   is fun  "));
        System.out.println(counter.countWords("   "));
        System.out.println(counter.countWords("one"));
        System.out.println(counter.countWords(null));
    }
}
