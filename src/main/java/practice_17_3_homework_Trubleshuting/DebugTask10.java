// Код должен удалять элементы списка, но выбрасывает ConcurrentModificationException.

package practice_17_3_homework_Trubleshuting;

import java.util.*;

public class DebugTask10 {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));
        names.removeIf(name -> name.startsWith("A"));
        System.out.println(names);
    }
}
/* было
        List<String> names = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));
        for (String name : names) {
            if (name.startsWith("A")) {
                names.remove(name);
            }
        }
*/