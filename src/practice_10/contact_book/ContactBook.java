package practice_10.contact_book;

/*
Пример 3. Система хранения контактов в формате "Имя - номер телефона". Порядок не важен. Без дублей.
Нужно находить, обновлять контакт
*/

import java.util.HashMap;

public class ContactBook {
    // клюс-значение (имя-телефон)
    private HashMap<String, Integer> contacts;

    public ContactBook() {
        this.contacts = new HashMap<>();
    }

    // добавить контакт
    public void addContact(String name, Integer phone) {
        contacts.put(name, phone);
    }

    // поиск контактов по имени
    public Integer getPhone(String name) {
        return contacts.get(name);
    }

    // обновление телефона по имени
    public  void updatePhone(String name, Integer updatedPhone) {
        contacts.put(name, updatedPhone);
    }

    public void printContacts() {
        System.out.println("Все контакты:");
        contacts.forEach(
                (name, phone) -> {
                    System.out.println("имя "+ name + ", телефон " + phone );
                }
        );
        System.out.println();
    }
}
