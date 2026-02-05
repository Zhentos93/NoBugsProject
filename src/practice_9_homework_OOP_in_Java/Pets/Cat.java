package practice_9_homework_OOP_in_Java.Pets;

public class Cat extends Pet {

    @Override
    public void feed() {
        System.out.println("Поиграть с кошкой");
    }

    public void interact() {
        System.out.println("Покормить влажным кормом");
    }
}
