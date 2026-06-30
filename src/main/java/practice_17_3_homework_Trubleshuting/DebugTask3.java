// Код должен напечатать числа от 1 до 5, но программа зависает.

package practice_17_3_homework_Trubleshuting;

public class DebugTask3 {
    public static void main(String[] args) {
        int i = 1;
        while (i <= 5) {
            i++;
            System.out.println("Number: " + (i-1));
        }
    }
}

/* было
public class DebugTask3 {
    public static void main(String[] args) {
        int i = 1;
        while (i <= 5) {
            System.out.println("Number: " + i);
        }
    }
}
*/
