package practice_17_2_practice_Trubleshuting.exception;

public class ArrayExceptionExample {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};

        for (int i = 0; i < numbers.length; i++) {  // Ошибка: i <= вместо i <
            System.out.println("Число: " + numbers[i]);
        }
    }
}

// было for (int i = 0; i <= numbers.length; i++)
