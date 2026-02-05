package practice_9_homework_OOP_in_Java.Museum;

public class Sculpture implements Exhibit{

    @Override
    public void preserve() {
        System.out.println("Нуждается в реставрации");
    }

    @Override
    public String describe() {
        return "Скульптурный объект";
    }
}