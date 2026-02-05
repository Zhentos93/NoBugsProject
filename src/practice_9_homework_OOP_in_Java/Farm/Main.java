package practice_9_homework_OOP_in_Java.Farm;

public class Main {
    public static void main(String[] args) {

        FarmAnimal cow = new Cow();
        FarmAnimal chicken = new Chicken();

        Farm farm = new Farm();

        farm.setAnimal(cow);
        farm.serviceAnimal();

        System.out.println(" ");

        farm.setAnimal(chicken);
        farm.serviceAnimal();
    }
}
