// Код должен напечатать числа от n до 1, но программа падает с StackOverflowError.

package practice_17_3_homework_Trubleshuting;

public class DebugTask6 {
    public static void main(String[] args) {
        countdown(5);
    }

    public static void countdown(int n) {
        System.out.println(n);
        if (n <= 5 && n > 1) {
            countdown(n - 1);
        }
    }
}

/* было
    System.out.println(n);
    countdown(n - 1);
*/
