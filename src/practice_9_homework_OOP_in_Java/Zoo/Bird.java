package practice_9_homework_OOP_in_Java.Zoo;

public class Bird extends Animal {

    @Override
    public void makeSound() {
        System.out.println("Птица чирикает");
    }

    public void move() {
        System.out.println("Птица летает");
    }
}
