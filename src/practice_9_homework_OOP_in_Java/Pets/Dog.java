package practice_9_homework_OOP_in_Java.Pets;

public class Dog extends Pet {

    @Override
    public void feed() {
        System.out.println("Погулять с собакой");
    }

    @Override
    public void interact() {
        System.out.println("Покормить сухим кормом");
    }
}
