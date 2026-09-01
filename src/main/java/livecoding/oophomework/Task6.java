package livecoding.oophomework;

import java.util.ArrayList;
import java.util.List;

abstract class DeliveryMethod {
    private String address;

    public DeliveryMethod(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public abstract void deliver();
}

class CourierMethod extends DeliveryMethod {
    public CourierMethod(String address) {
        super(address);
    }

    @Override
    public void deliver() {
        System.out.println("Курьер доставляет посылку на " + getAddress());
    }
}

class DroneMethod extends DeliveryMethod {
    public DroneMethod(String address) {
        super(address);
    }

    @Override
    public void deliver() {
        System.out.println("Дрон летит с посылкой на " + getAddress());
    }
}

class MailMethod extends DeliveryMethod {
    public MailMethod(String address) {
        super(address);
    }

    @Override
    public void deliver() {
        System.out.println("Почта отправила посылку через сортировочный центр " + getAddress());
    }
}

class DeliverSystem {
    private List<DeliveryMethod> deliveries;

    public DeliverSystem(List<DeliveryMethod> deliveries) {
        this.deliveries = deliveries;
    }

    public void deliverAll() {
        for (DeliveryMethod deliveryMethod : deliveries) {
            deliveryMethod.deliver();
        }
    }
}

public class Task6 {
    public static void main(String[] args) {
        List<DeliveryMethod> deliveries = new ArrayList<>();

        deliveries.add(new CourierMethod("ул. Ленина"));
        deliveries.add(new DroneMethod("ул. Пушкина"));
        deliveries.add(new MailMethod("ул. Гагарина"));

        DeliverSystem deliverSystem = new DeliverSystem(deliveries);

        deliverSystem.deliverAll();
    }
}
