package practice_9_homework_OOP_in_Java.AmusementPark;

import org.w3c.dom.Attr;

public class AmusementPark {

    private Attraction attraction;

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public void operateAttraction() {
        System.out.println(attraction.info());
        attraction.maintain();
    }
}
