package сode_reading.CodeReading_Cycles;

public class A36 {
    public static void main(String[] args) {
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 3; j++) {
                if (j == 2) {
                    break;
                }

                System.out.print(i + "" + j + " ");
            }
        }
    }
}
