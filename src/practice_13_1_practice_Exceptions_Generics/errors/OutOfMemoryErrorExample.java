package practice_13_1_practice_Exceptions_Generics.errors;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryErrorExample {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        while (true) {
            list.add(new byte[1024 * 1024]); // добавляем 1 МБ в список бесконечно
        }
    }
}
