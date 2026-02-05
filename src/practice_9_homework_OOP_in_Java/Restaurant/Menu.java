package practice_9_homework_OOP_in_Java.Restaurant;

public class Menu {

    private Dish dish;

    public void addDish(Dish dish) {
        this.dish = dish;
    }

    public Dish getDish() {
        return dish;
    }

    public void printMenu() {
        System.out.println(dish.getDescription());
    }
}
