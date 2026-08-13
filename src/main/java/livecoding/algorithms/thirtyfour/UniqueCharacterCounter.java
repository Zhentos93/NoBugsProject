package livecoding.algorithms.thirtyfour;

/*
 * Задача №34. Подсчёт уникальных символов
 *
 * Найти количество уникальных символов в строке.
 */

import java.util.HashSet;
import java.util.Set;

public class UniqueCharacterCounter {

    public UniqueCharacterCounter() {
    }

    int uniqueChars(String s) {
        Set<Character> set = new HashSet<>(); // Храним уникальные символы

        for (char c : s.toCharArray()) { // Перебираем символы строки
            set.add(c);                  // Добавляем символ во множество
        }

        return set.size(); // Возвращаем количество уникальных символов
    }

    public static void main(String[] args) {
        UniqueCharacterCounter counter =
                new UniqueCharacterCounter();

        System.out.println(counter.uniqueChars("hello"));
        System.out.println(counter.uniqueChars("Java"));
        System.out.println(counter.uniqueChars("aA"));
        System.out.println(counter.uniqueChars(""));
        System.out.println(counter.uniqueChars("a a"));
    }
}
