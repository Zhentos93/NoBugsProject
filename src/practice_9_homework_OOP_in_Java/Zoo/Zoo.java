package practice_9_homework_OOP_in_Java.Zoo;

public class Zoo {

    private Animal animal;

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public void showAnimalBehavior() {

        animal.makeSound();
        animal.move();
    }
}