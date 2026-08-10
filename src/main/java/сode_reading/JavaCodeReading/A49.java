package сode_reading.JavaCodeReading;

class User5 {
    String name;

    User5(String name) {
        this.name = name;
    }
}

public class A49 {
    public static void main(String[] args) {
        User5 a = new User5("Alex");
        User5 b = new User5("Alex");

        System.out.println(a.equals(b));
    }
}
