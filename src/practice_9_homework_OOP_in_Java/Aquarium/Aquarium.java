package practice_9_homework_OOP_in_Java.Aquarium;

public class Aquarium {

    private SeaCreature creature;

    public void setCreature(SeaCreature creature) {
        this.creature = creature;
    }

    public void showMovement() {
        creature.move();
    }
}
