package practice_9_homework_OOP_in_Java.Farm;

public class Cow implements FarmAnimal {

    public void feed() {
        System.out.println("Ест траву");
    }

    public void care() {
        System.out.println("Нужно пасти");
    }

    public void produce() {
        System.out.println("Даёт молоко");
    }
}
