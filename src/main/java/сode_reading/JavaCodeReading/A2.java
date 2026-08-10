package сode_reading.JavaCodeReading;

public class A2 {
    int x = 10;

    {
        x = 20;
    }

    A2() {
        x = 30;
    }

    public static void main(String[] args) {
        A2 m = new A2();
        System.out.println(m.x);
    }
}
