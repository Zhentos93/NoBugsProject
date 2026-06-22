package practice_9_homework_OOP_in_Java.AmusementPark;

public class Main {
    public static void main(String[] args) {

        Attraction rollerCoaster = new RollerCoaster();
        Attraction carousel = new Carousel();

        AmusementPark amusementPark = new AmusementPark();

        amusementPark.setAttraction(rollerCoaster);
        amusementPark.operateAttraction();

        System.out.println(" ");

        amusementPark.setAttraction(carousel);
        amusementPark.operateAttraction();
    }
}
