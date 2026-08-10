package сode_reading.JavaCodeReading;

class Test1 {
    synchronized void print() {
        System.out.println("Hello");
    }
}

public class A63 {
    public static void main(String[] args) {
        Test1 test = new Test1();
        test.print();
    }
}
