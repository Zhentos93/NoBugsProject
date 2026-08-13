package livecoding.algorithms.fourtythree;

/*
 * Задача №43. Подсчёт гласных
 *
 * Подсчитать количество гласных в строке.
 */

// Ошибки
/*
 * Ошибки в задаче №43 — подсчёт гласных:
 *
 * 1. Создана переменная clean, но она не используется.
 *
 * 2. trim() не требуется по условию задачи.
 *
 * 3. Не сохранена строка английских гласных:
 *    String vowels = "aeiou";
 *
 * 4. Символы строки не перебираются.
 *
 * 5. Не выполняется проверка символа через indexOf().
 *
 * 6. Счётчик count нигде не увеличивается,
 *    поэтому метод всегда возвращает 0.
 */

public class EnglishVowel {
    public EnglishVowel() {
    }

    public int countVowels(String s) {
        int count = 0;
        String vowels = "aeiou";

        for (char c : s.toLowerCase().toCharArray()) {
            if (vowels.indexOf(c) >= 0) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        EnglishVowel englishVowel = new EnglishVowel();

        System.out.println(englishVowel.countVowels("Hello World"));
        System.out.println(englishVowel.countVowels("AEIOU"));
        System.out.println(englishVowel.countVowels("rhythm"));
        System.out.println(englishVowel.countVowels("Java 17"));
        System.out.println(englishVowel.countVowels(""));
    }
}
