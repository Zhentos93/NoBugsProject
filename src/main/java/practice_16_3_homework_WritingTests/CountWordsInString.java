// Напишите тесты для метода, который считает количество слов в строке:

/*
Тесты должны проверять:
Пустую строку
null
Строку с несколькими пробелами
*/

package practice_16_3_homework_WritingTests;

public class CountWordsInString {

    public int countWords(String sentence) {
        return sentence.trim().isEmpty() ? 0 : sentence.split("\\s+").length;
    }
}
