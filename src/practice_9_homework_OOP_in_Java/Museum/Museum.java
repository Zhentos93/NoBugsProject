package practice_9_homework_OOP_in_Java.Museum;

public class Museum {

    private Exhibit exhibit;

    public void setExhibit(Exhibit exhibit) {
        this.exhibit = exhibit;
    }

    public void showExhibit() {

        System.out.println(exhibit.describe());
        exhibit.preserve();
    }
}
