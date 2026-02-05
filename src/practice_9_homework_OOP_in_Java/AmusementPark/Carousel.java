package practice_9_homework_OOP_in_Java.AmusementPark;

public class Carousel implements Attraction {

    @Override
    public String info() {
        return "Спокойный аттракцион";
    }

    @Override
    public void maintain() {
        System.out.println("Требует технического обслуживания");
    }
}
