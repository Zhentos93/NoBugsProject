package сode_reading.JavaCodeReading;

public class A17 {
    static class Animal {
        void speak() {
            System.out.println("Animal");
        }
    }

    static class Dog extends Animal {
        @Override
        void speak() {
            System.out.println("Dog");
        }
    }

    public static void main(String[] args) {
        Animal animal = new Dog();

        animal.speak();
    }
}
