package practice_9_homework_OOP_in_Java.AmusementPark;

public class RollerCoaster implements Attraction {

    @Override
    public String info() {
        return "Острые ощущения";
    }

    @Override
    public void maintain() {
        System.out.println("Требует проверки безопасности");
    }
}
