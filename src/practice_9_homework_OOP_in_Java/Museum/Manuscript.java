package practice_9_homework_OOP_in_Java.Museum;

public class Manuscript implements Exhibit {

    @Override
    public void preserve() {
        System.out.println("Требует контролируемой влажности");
    }

    @Override
    public String describe() {
        return "Древний текст";
    }
}
