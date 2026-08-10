package сode_reading.JavaCodeReading;

public class A41 {
    public static void main(String[] args) {
        for (int i = 1; i <= 2; i++) { //i=1, i=2
            for (int j = 1; j <= 3; j++) { //j=1,
                if (j == 2) {
                    break;
                }

                System.out.print(i + "" + j + " ");
            }
        }
    }
}
