package livecoding.algorithms.twentysix;

/*
 * Задача №26. Подсчёт частоты слов в строке
 *
 * Подсчитать, сколько раз каждое слово
 * встречается в строке.
 */

import java.util.HashMap;
import java.util.Map;

public class WordFrequencyCounter {

    public WordFrequencyCounter() {
    }

    Map<String, Integer> wordFreq(String s) {
        Map<String, Integer> map = new HashMap<>(); // Храним слово и его частоту

        for (String word : s.toLowerCase().split("\\s+")) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        return map; // Возвращаем частотный словарь
    }

    public static void main(String[] args) {
        WordFrequencyCounter counter = new WordFrequencyCounter();

        System.out.println(counter.wordFreq("Java is fun Java"));
        System.out.println(counter.wordFreq("one one ONE two"));
        System.out.println(counter.wordFreq(""));
    }
}
