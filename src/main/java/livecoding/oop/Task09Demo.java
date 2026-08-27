package livecoding.oop;

import java.util.List;

// Абстрактный: название общее, но «транспорт вообще» не имеет одного способа движения
abstract class Transport {
    private final String name;

    public Transport(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Абстрактный: каждый вид транспорта движется по-своему
    public abstract void move();
}

class Car extends Transport {
    public Car(String name) {
        super(name);
    }

    @Override
    public void move() {
        System.out.println("Машина " + getName() + " едет по дороге");
    }
}

class Bicycle extends Transport {
    public Bicycle(String name) {
        super(name);
    }

    @Override
    public void move() {
        System.out.println("Велосипед " + getName() + " крутит педали");
    }
}

class Airplane extends Transport {
    public Airplane(String name) {
        super(name);
    }

    @Override
    public void move() {
        System.out.println("Самолет " + getName() + " летит по воздуху");
    }
}

class TransportService {
    public void startAll(List<Transport> transports) {
        for (Transport transport : transports) {
            transport.move(); // Вызывается move() конкретного транспорта
        }
    }
}

public class Task09Demo {
    public static void main(String[] args) {
        List<Transport> transports = List.of(
                new Car("Tesla"),
                new Bicycle("BMX"),
                new Airplane("Boeing")
        );

        TransportService transportService = new TransportService();
        transportService.startAll(transports);
    }
}