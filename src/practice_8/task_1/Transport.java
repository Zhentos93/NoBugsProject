package practice_8.task_1;

public abstract class Transport {
    private double speed; // км/ч
    private int capacity; // в метрах в кубе

    public Transport(int speed, int capacity) {
        this.speed = speed;
        this.capacity = capacity;
    }

    public double getSpeed() {
        return this.speed;
    }

    public int getCapacity() {
        return this.capacity;
    }

    abstract void start();
}
