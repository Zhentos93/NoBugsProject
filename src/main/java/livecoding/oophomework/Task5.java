package livecoding.oophomework;

import java.util.ArrayList;
import java.util.List;

abstract class Pet {
    private String name;

    public Pet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract public void eat();

    abstract public void sleep();
}

class Cat extends Pet {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println(getName() + " ест рыбу");
    }

    @Override
    public void sleep() {
        System.out.println(getName() + " спит на подоконнике");
    }
}

class Dog extends Pet {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println(getName() + " ест мясо");
    }

    @Override
    public void sleep() {
        System.out.println(getName() + " спит в будке");
    }
}

class Dragon extends Pet {
    public Dragon(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println(getName() + " ест уголь");
    }

    @Override
    public void sleep() {
        System.out.println(getName() + " спит в пещере");
    }
}

class PetService {
    private List<Pet> pets;

    public PetService(List<Pet> pets) {
        this.pets = pets;
    }

    public void eatAll() {
        for (Pet pet : pets) {
            pet.eat();
        }
    }

    public void sleepAll() {
        for (Pet pet : pets) {
            pet.sleep();
        }
    }
}

public class Task5 {
    public static void main(String[] args) {
        List<Pet> pets = new ArrayList<>();

        pets.add(new Cat("Барсик"));
        pets.add(new Dog("Шарик"));
        pets.add(new Dragon("Дрого"));

        PetService petService = new PetService(pets);

        petService.eatAll();
        petService.sleepAll();
    }
}
