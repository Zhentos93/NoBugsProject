package practice_9_homework_OOP_in_Java.Farm;

public class Farm {

    private FarmAnimal farmAnimal;

    public void setAnimal(FarmAnimal farmAnimal) {
        this.farmAnimal = farmAnimal;
    }

    public void serviceAnimal() {

        farmAnimal.feed();
        farmAnimal.care();
        farmAnimal.produce();
    }
}
