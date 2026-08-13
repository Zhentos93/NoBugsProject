package livecoding.algorithms.ten;

/*
 * Задача №10. Подсчёт слов в строке
 *
 * Посчитать количество слов в строке.
 */

// Ошибки
/*
 * Код до подсказки не присылался.
 * Для начала решения потребовался разбор следующих конструкций:
 *
 * 1. Проверка null и строки из пробелов.
 * 2. trim() для удаления пробелов по краям.
 * 3. split("\\s+") для разбиения по группам пробелов.
 * 4. length для получения количества частей массива.
 */

public class WordCou {
    public WordCou() {
    }

    public int countWords(String s) {

        if (s == null || s.trim().isEmpty()) {
            return 0;
        }

        return s.trim().split("\\s+").length;
    }

    public static void main(String[] args) {
        WordCou wordCou = new WordCou();

        System.out.println(wordCou.countWords("Java   is fun"));
        System.out.println(wordCou.countWords("  Java   is fun  "));
        System.out.println(wordCou.countWords("   "));
        System.out.println(wordCou.countWords("one"));
        System.out.println(wordCou.countWords(null));
    }
}
