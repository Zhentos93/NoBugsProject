package сode_reading.JavaCodeReading;

public class A88 {
    public static void main(String[] args) {
        for (int i = 1; i <= 6; i++) {
            if (i == 2) {
                continue;
            }

            if (i == 5) {
                break;
            }

            System.out.print(i + " ");
        }
    }
}
