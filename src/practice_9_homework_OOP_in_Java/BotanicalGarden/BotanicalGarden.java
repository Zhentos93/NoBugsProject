package practice_9_homework_OOP_in_Java.BotanicalGarden;

public class BotanicalGarden {

    private Plant plant;

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public void maintainPlant() {
        plant.care();
    }
}
