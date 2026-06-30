package practice_17_2_practice_Trubleshuting.mistake;

public class PrintNumbers {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            printNumber(i);
        }
    }

    public static void printNumber(int number) {
        System.out.println(number);
    }
}

// было System.out.println(number + 1)
