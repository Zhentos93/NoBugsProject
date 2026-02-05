package practice_9_homework_OOP_in_Java.Aquarium;

public class Main {
    public static void main(String[] args) {

        SeaCreature shark = new Shark();
        SeaCreature starfish = new Starfish();

        Aquarium employee = new Aquarium();

        employee.setCreature(shark);
        employee.showMovement();

        System.out.println(" ");

        employee.setCreature(starfish);
        employee.showMovement();
    }
}
