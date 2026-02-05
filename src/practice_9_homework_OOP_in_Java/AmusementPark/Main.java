package practice_9_homework_OOP_in_Java.AmusementPark;

import org.w3c.dom.Attr;
import practice_9_homework_OOP_in_Java.Zoo.Animal;

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
