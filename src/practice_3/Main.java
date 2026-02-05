package practice_3;

public class Main {

    public static void main (String[] args) {
        // класс Car
        Car Lada = new Car ("Lada", 2020);
        Lada.print();
        Lada.setYear(2025);
        Lada.print();

        // класс Rectangle
        Rectangle pryam = new Rectangle (3, 5);
        pryam.setWidth(5);
        pryam.print();

        // класс Book
        Book fantastic = new Book("Братья Карамазовы", "Фёдор Достоевский");
        fantastic.setAuthor("Пушкин");
        fantastic.printInfo();

        // класс BankAccount
        BankAccount petya = new BankAccount("Петя", 1000);
        petya.deposit(300.3);
        petya.withdraw(200.1);
        petya.printBalance();

        // класс Point
        Point coordinate = new Point(5.55, 5.56);
        coordinate.setX(5.57);
        coordinate.print();

        //класс StudentGroup
        StudentGroup studentGroup = new StudentGroup("math", 10);
        studentGroup.setStudentCount(15);
        studentGroup.printInfo();

        // класс Circle
        Circle circle = new Circle(5.2);
        circle.setRadius(6);
        circle.print();

        // класс Teacher
        Teacher school1 = new Teacher("Иванов", "информатика");
        school1.setSubject("литература");
        school1.printInfo();

        // класс Product
        Product iPhone = new Product("iPhone", 100000);
        iPhone.setPrice(90000);
        iPhone.applyDiscount(20);
        iPhone.printInfo();

        // класс Laptop
        Laptop laptop = new Laptop("Lenovo", 50000);
        laptop.setPrice(40000);
        laptop.printInfo();
    }
}
