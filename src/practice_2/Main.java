package practice_2;

public class Main {
    public static void main(String[] args) {
        // проверка конструктора по умолчанию
        Student petya = new Student(18, "Петя");

        // System.out.println("Возраст Пети: " + petya.age); // убрали, т.к. есть метод print(), который мы вызвали;
        // System.out.println("Имя Пети: " + petya.name); // убрали, т.к. есть метод print(), который мы вызвали;

        petya.print(); // мы создали отдельный метод для распечатывания, чтобы постоянно не печатать System.out.println

        Student kolya = new Student(20, "Коля");

        // System.out.println("Возраст Коли: " + kolya.age); // убрали, т.к. есть метод print(), который мы вызвали;
        // System.out.println("Имя Коли: " + kolya.name); // убрали, т.к. есть метод print(), который мы вызвали;

        kolya.print();

        petya.setName("Антон"); //
        // System.out.println("Новое имя Пети: " + petya.name); убрали, т.к. есть метод print(), который мы вызвали;
        // petya.name = "Антон"; // можно напрямую задать значение, но лучше использовать сеттеры
        petya.print();

        kolya.setAge(21);
        // System.out.println("Новый возраст Коли: " + kolya.age); // убрали, т.к. есть метод print(), который мы вызвали;
        // kolya.age = 21; // можно напрямую задать значение, но лучше использовать сеттеры
        kolya.print();

        // дебаггинг или дебаг = debug - исправление дефекта, точка ОСТАНОВА
    }
}
