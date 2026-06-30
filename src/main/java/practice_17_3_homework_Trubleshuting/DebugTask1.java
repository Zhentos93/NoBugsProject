// Код должен напечатать все элементы массива, но вместо этого выбрасывает исключение.

package practice_17_3_homework_Trubleshuting;

public class DebugTask1 {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 50};
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
    }
}

// было for (int i = 0; i <= numbers.length; i++)
