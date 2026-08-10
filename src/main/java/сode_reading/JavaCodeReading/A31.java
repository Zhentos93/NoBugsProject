package сode_reading.JavaCodeReading;

abstract class Animal {
    Animal() {
        System.out.println("Animal constructor");
    }

    abstract void speak();
}

class Cat extends Animal {
    Cat() {
        System.out.println("Cat constructor");
    }

    @Override
    void speak() {
        System.out.println("Meow");
    }
}

public class A31 {
    public static void main(String[] args) {
        Animal animal = new Cat();
        animal.speak();
    }
}
