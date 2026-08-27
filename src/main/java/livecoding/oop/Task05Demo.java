package livecoding.oop;

import java.util.List;

// Абстрактный: хранит общее имя, но «просто питомца» с единым поведением нет
abstract class Pet {
    private final String name;

    public Pet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Абстрактный: каждый вид питомца ест по-своему
    public abstract void eat();

    // Абстрактный: каждый вид питомца спит по-своему
    public abstract void sleep();
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

class PetCareService {
    public void feedAll(List<Pet> pets) {
        for (Pet pet : pets) {
            pet.eat(); // Каждый питомец использует собственную реализацию eat()
        }
    }

    public void putAllToSleep(List<Pet> pets) {
        for (Pet pet : pets) {
            pet.sleep(); // Каждый питомец использует собственную реализацию sleep()
        }
    }
}

public class Task05Demo {
    public static void main(String[] args) {
        List<Pet> pets = List.of(
                new Cat("Барсик"),
                new Dog("Шарик"),
                new Dragon("Дрого")
        );

        PetCareService service = new PetCareService();
        service.feedAll(pets);
        service.putAllToSleep(pets);
    }
}
