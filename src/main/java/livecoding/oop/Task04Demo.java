package livecoding.oop;

import java.util.List;

// Интерфейс: отправителю нужен только общий контракт, а не общие поля или реализация
interface Notification {
    // Метод без реализации: каждый канал отправляет уведомление по-своему
    public void send();
}

class EmailNotification implements Notification {
    private final String recipient;
    private final String message;

    public EmailNotification(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }

    @Override
    public void send() {
        System.out.println("Email отправлен на " + recipient + ": " + message);
    }
}

class SmsNotification implements Notification {
    private final String recipient;
    private final String message;

    public SmsNotification(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }

    @Override
    public void send() {
        System.out.println("SMS отправлено на " + recipient + ": " + message);
    }
}

class PushNotification implements Notification {
    private final String recipient;
    private final String message;

    public PushNotification(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }

    @Override
    public void send() {
        System.out.println("Push отправлено на " + recipient + ": " + message);
    }
}

class NotificationSender {
    public void sendAll(List<Notification> notifications) {
        for (Notification notification : notifications) {
            notification.send(); // Вызывается send() конкретного вида уведомления
        }
    }
}

public class Task04Demo {
    public static void main(String[] args) {
        List<Notification> notifications = List.of(
                new EmailNotification("alex@test.com", "Ваш заказ готов"),
                new SmsNotification("+123456789", "Код подтверждения 1234"),
                new PushNotification("device-777", "У вас новое сообщение")
        );

        NotificationSender notificationSender =
                new NotificationSender();

        notificationSender.sendAll(notifications);
    }
}
