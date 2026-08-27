package сode_reading.CodeReading_Cycles;

public class A37 {
    public static void main(String[] args) {
        for (int i = 1; i <= 3; i++) {
            if (i == 2) {
                continue;
            }

            for (int j = 1; j <= 2; j++) {
                System.out.print(i + "" + j + " ");
            }
        }
    }
}
