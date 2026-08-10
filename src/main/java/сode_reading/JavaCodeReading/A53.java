package сode_reading.JavaCodeReading;

class User6 {
    static int count = 0;

    User6() {
        count++;
    }
}

public class A53 {
    public static void main(String[] args) {
        new User6();
        new User6();
        new User6();

        System.out.println(User6.count);
    }
}
