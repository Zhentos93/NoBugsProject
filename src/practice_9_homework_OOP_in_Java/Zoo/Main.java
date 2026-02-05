package practice_9_homework_OOP_in_Java.Zoo;

public class Main {
    public static void main(String[] args) {

        Animal elephant = new Elephant();
        Animal bird = new Bird();

        Zoo zoo = new Zoo();

        zoo.setAnimal(elephant);
        zoo.showAnimalBehavior();

        System.out.println(" ");

        zoo.setAnimal(bird);
        zoo.showAnimalBehavior();
    }
}
