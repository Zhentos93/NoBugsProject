package practice_9_homework_OOP_in_Java.BotanicalGarden;

public class Main {
    public static void main(String[] args) {

        Plant orchid = new Orchid();
        Plant cactus = new Cactus();

        BotanicalGarden botanicalGarden = new BotanicalGarden();

        botanicalGarden.setPlant(orchid);
        botanicalGarden.maintainPlant();

        System.out.println(" ");

        botanicalGarden.setPlant(cactus);
        botanicalGarden.maintainPlant();
    }
}
