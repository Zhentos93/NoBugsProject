package practice_5;

public class GameSettings {
    static int maxPlayers = 2; // общее ограничение игроков
    final String gameName; // название
    int currentPlayers; // сколько игроков в игре сейчас

    GameSettings(String gameName, int currentPlayers) {
        this.gameName = gameName;
        this.currentPlayers = currentPlayers;
    }
    static void setMaxPlayers(int newMax) {
        maxPlayers = newMax;
    }
    void addPlayer() {
        if (currentPlayers < maxPlayers) {
            currentPlayers++;
        } else {
            System.out.println("Нельзя добавить игрока: достигнут лимит " + maxPlayers);
        }
    }
    public void printGameStatus() {
        System.out.println("Название игры: " + this.gameName + ", текущее количество игроков: " + this.currentPlayers + ", максимальное количество игроков: " + GameSettings.maxPlayers);
    }
}
