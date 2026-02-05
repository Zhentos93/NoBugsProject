package practice_9_homework_OOP_in_Java.Restaurant;

public class HotDish extends Dish {
    private final int temperature;

    public HotDish(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public String getDescription() {
        return "Горячее блюдо, " + "температура блюда: " + getTemperature() + "°С";
    }
}
