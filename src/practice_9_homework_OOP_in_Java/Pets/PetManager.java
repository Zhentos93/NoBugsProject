package practice_9_homework_OOP_in_Java.Pets;

public class PetManager {

    private Pet pet;

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void handlePet() {
        pet.feed();
        pet.interact();
    }
}
