package livecoding.oop;

import java.util.List;

// Абстрактный: адрес общий, но единого способа доставки не существует
abstract class Delivery {
    private final String address;

    public Delivery(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    // Абстрактный: курьер, дрон и почта доставляют посылку по-разному
    public abstract void deliver();
}

class CourierDelivery extends Delivery {
    public CourierDelivery(String address) {
        super(address);
    }

    @Override
    public void deliver() {
        System.out.println("Курьер доставляет посылку на " + getAddress());
    }
}

class DroneDelivery extends Delivery {
    public DroneDelivery(String address) {
        super(address);
    }

    @Override
    public void deliver() {
        System.out.println("Дрон летит с посылкой на " + getAddress());
    }
}

class PostDelivery extends Delivery {
    public PostDelivery(String address) {
        super(address);
    }

    @Override
    public void deliver() {
        System.out.println("Почта отправила посылку через сортировочный центр на "
                + getAddress());
    }
}

class DeliveryService {
    public void sendAll(List<Delivery> deliveries) {
        for (Delivery delivery : deliveries) {
            delivery.deliver(); // Вызывается способ доставки фактического объекта
        }
    }
}

public class Task06Demo {
    public static void main(String[] args) {
        List<Delivery> deliveries = List.of(
                new CourierDelivery("ул. Ленина"),
                new DroneDelivery("ул. Пушкина"),
                new PostDelivery("ул. Гагарина")
        );

        DeliveryService deliveryService = new DeliveryService();
        deliveryService.sendAll(deliveries);
    }
}