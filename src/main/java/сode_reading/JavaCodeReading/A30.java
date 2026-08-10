package сode_reading.JavaCodeReading;

class User3 {
    String name;

    User3() {
        this("Unknown");
        System.out.println("Default");
    }

    User3(String name) {
        this.name = name;
        System.out.println("With name");
    }
}

public class A30 {
    public static void main(String[] args) {
        User3 user3 = new User3();

        System.out.println(user3.name);
    }
}
