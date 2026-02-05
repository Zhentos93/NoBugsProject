package practice_9_homework_OOP_in_Java.Pets;

public class Main {
    public static void main(String[] args) {

        Pet dog = new Dog();
        Pet cat = new Cat();

        PetManager petManager = new PetManager();

        petManager.setPet(dog);
        petManager.handlePet();

        System.out.println(" ");

        petManager.setPet(cat);
        petManager.handlePet();
    }
}
