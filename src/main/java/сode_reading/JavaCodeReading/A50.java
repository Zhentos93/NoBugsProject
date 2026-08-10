package сode_reading.JavaCodeReading;

public class A50 {
    static class Animal {
    }

    static class Dog extends Animal {
    }

    public static void main(String[] args) {
        Animal animal = new Dog();

        System.out.println(animal instanceof Animal);
        System.out.println(animal instanceof Dog);
    }
}
