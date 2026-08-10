package livecoding.algorithms.seven;

/*
 * Задача №7. Подсчёт гласных
 *
 * Посчитать количество гласных букв в строке.
 */

public class VowelCounter {

    public VowelCounter() {
    }

    int countVowels(String s) {
        int count = 0; // Начальное количество найденных гласных

        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiouаеёиоуыэюя".indexOf(c) >= 0) {
                count++; // Увеличиваем счётчик найденных гласных
            }
        }

        return count; // Возвращаем количество гласных
    }

    public static void main(String[] args) {
        VowelCounter counter = new VowelCounter();

        System.out.println(counter.countVowels("Hello World"));
        System.out.println(counter.countVowels("Привет, Java"));
        System.out.println(counter.countVowels("rhythm"));
        System.out.println(counter.countVowels(""));
    }
}
