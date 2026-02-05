package practice_9_homework_OOP_in_Java.Farm;

public class Chicken implements FarmAnimal {
    public void feed() {
        System.out.println("Ест зерно");
    }

    public void care() {
        System.out.println("Нужна кормушка");
    }

    public void produce() {
        System.out.println("Несёт яйца");
    }
}
