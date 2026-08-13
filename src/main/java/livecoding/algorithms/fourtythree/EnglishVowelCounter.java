package livecoding.algorithms.fourtythree;

/*
 * Задача №43. Подсчёт гласных
 *
 * Подсчитать количество гласных в строке.
 */

public class EnglishVowelCounter {

    public EnglishVowelCounter() {
    }

    public int countVowels(String s) {
        int count = 0;
        String vowels = "aeiou"; // Английские гласные

        for (char c : s.toLowerCase().toCharArray()) {
            if (vowels.indexOf(c) >= 0) { // Символ найден среди гласных
                count++;
            }
        }

        return count; // Возвращаем количество гласных
    }

    public static void main(String[] args) {
        EnglishVowelCounter counter =
                new EnglishVowelCounter();

        System.out.println(counter.countVowels("Hello World"));
        System.out.println(counter.countVowels("AEIOU"));
        System.out.println(counter.countVowels("rhythm"));
        System.out.println(counter.countVowels("Java 17"));
        System.out.println(counter.countVowels(""));
    }
}
