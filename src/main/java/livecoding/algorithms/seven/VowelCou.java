package livecoding.algorithms.seven;

/*
 * Задача №7. Подсчёт гласных
 *
 * Посчитать количество гласных букв в строке.
 */

// Ошибок при решении нет

public class VowelCou {
    public VowelCou() {
    }

    public int countVowels(String s) {
        int count = 0;
        String vowels = "aeiouаеёиоуыэюя";

        for (char c : s.toLowerCase().toCharArray()) {
            if (vowels.indexOf(c) >= 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        VowelCou vowelCou = new VowelCou();

        System.out.println(vowelCou.countVowels("Hello World"));
        System.out.println(vowelCou.countVowels("Привет, Java"));
        System.out.println(vowelCou.countVowels("rhythm"));
        System.out.println(vowelCou.countVowels(""));
    }
}
