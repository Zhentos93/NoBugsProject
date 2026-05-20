/*Задание 1: дана коллекция строк Arrays.asList("a1", "a2", "a3", "a1")
    - вернуть количество вхождений объекта "a1"
    - вернуть первый элемент коллекции или 0, если коллекция пуста
    - вернуть последний элемент коллекции или "empty", если коллекция пуста
    - найти элемент в коллекции равный "a3"
    - вернуть третий элемент коллекции по порядку
    - вернуть два элемента начиная с второго*/

package practice_15_1_lecture_FunctionalInterface_Lambda_StreamAPI.streamapi;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class Task3_JavaGuru {
    public static void main(String[] args) {
        Collection collection = Arrays.asList("a1", "a2", "a3", "a1");
        System.out.println(collection.stream().filter("a1"::equals).count());
        System.out.println(collection.stream().findFirst().orElse(0));
        System.out.println(collection.stream().skip(collection.size() - 1).findFirst().orElse("empty"));
        System.out.println(collection.stream().filter("a3"::equals).findFirst().get());
        System.out.println(collection.stream().skip(2).findFirst().get());
        System.out.println(collection.stream().skip(1).limit(2).collect(Collectors.toList()));
    }
}
