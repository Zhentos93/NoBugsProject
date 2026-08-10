package сode_reading.JavaCodeReading;

class Test {
    static {
        System.out.println("Static");
    }

    {
        System.out.println("Instance");
    }

    Test() {
        System.out.println("Constructor");
    }
}

public class A14 {
    public static void main(String[] args) {
        new Test1();
        new Test1();
    }
}
