package livecoding.algorithms.twentyfive;

/*
 * Задача №25. Конкатенация строк через StringBuilder
 *
 * Собрать строку из массива слов,
 * разделяя слова пробелами.
 */

public class WordJoiner {

    public WordJoiner() {
    }

    String join(String[] words) {
        StringBuilder sb = new StringBuilder(); // Накапливаем результат

        for (int i = 0; i < words.length; i++) { // Перебираем слова по индексам
            sb.append(words[i]);                  // Добавляем текущее слово

            if (i != words.length - 1) { // Проверяем, что слово не последнее
                sb.append(" ");          // Добавляем пробел между словами
            }
        }

        return sb.toString(); // Преобразуем результат в String
    }

    public static void main(String[] args) {
        WordJoiner joiner = new WordJoiner();

        System.out.println(joiner.join(
                new String[]{"Java", "is", "fun"}
        ));
        System.out.println(joiner.join(new String[]{"Java"}));
        System.out.println(joiner.join(new String[]{}));
    }
}
