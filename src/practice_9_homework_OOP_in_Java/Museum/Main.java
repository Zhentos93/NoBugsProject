package practice_9_homework_OOP_in_Java.Museum;

public class Main {
    public static void main(String[] args) {

        Exhibit manuscript = new Manuscript();
        Exhibit sculpture = new Sculpture();

        Museum museum = new Museum();

        museum.setExhibit(manuscript);
        museum.showExhibit();

        System.out.println(" ");

        museum.setExhibit(sculpture);
        museum.showExhibit();
    }
}
