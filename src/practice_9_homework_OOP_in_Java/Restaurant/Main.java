package practice_9_homework_OOP_in_Java.Restaurant;

public class Main {
    public static void main(String[] args) {

        HotDish carbonara = new HotDish(29);
        Drink pinaColada = new Drink(200);

        Menu menu = new Menu();

        menu.addDish(carbonara);
        menu.printMenu();

        System.out.println(" ");

        menu.addDish(pinaColada);
        menu.printMenu();
    }
}
