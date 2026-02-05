package practice_9_homework_OOP_in_Java.Restaurant;

public class Drink extends Dish {
    private final int volume;

    public Drink(int volume) {
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public String getDescription() {
        return "Напиток, " + "объем напитка: " + getVolume() + "мл.";
    }
}
