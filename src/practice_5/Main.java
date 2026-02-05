package practice_5;

public class Main {
    public static void main(String[] args) {
        // класс Company
        System.out.println(" ");
        Company.printCompanyName();
        Company.companyName = "LADA";
        Company Ford = new Company(5, "Иван");
        Ford.printCompany();
        Company Audi = new Company(6, "Серёга");
        Audi.printCompany();

        // класс MathConstants
        System.out.println(" ");
        System.out.println("Площадь круга: " + MathConstants.calculateCircleArea(5.5));
        System.out.println("Площадь круга new: " + MathConstants.calculateCircleArea(5.88));
        System.out.println("Длина окружности: " + MathConstants.calculateCircumference(5.5));
        System.out.println("Длина окружности new: " + MathConstants.calculateCircumference(5.8));

        // класс LibraryTest
        System.out.println(" ");
        Library book = new Library("Братья Карамазовы", "Достоевский", 1812, "роман");
        System.out.println(book.getBookTitle() + " " +  book.author + " " + book.year + " " + book.category);

        // класс University
        System.out.println(" ");
        University Petrov = new University(1, "Иван");
        University Ivanov = new University(2, "Петя");
        University Sidorov = new University(3, "Алексей");
        Petrov.printStudentInfo();
        Ivanov.printStudentInfo();
        Sidorov.printStudentInfo();
        University.changeUniversityName("Harvard");
        Petrov.printStudentInfo();
        Ivanov.printStudentInfo();
        Sidorov.printStudentInfo();

        // класс GameSettings
        System.out.println(" ");
        GameSettings soccer = new GameSettings("футбол", 1);
        GameSettings basketball = new GameSettings("баскетбол", 1);
        soccer.printGameStatus();
        soccer.addPlayer();
        soccer.printGameStatus();
        soccer.addPlayer();
        GameSettings.setMaxPlayers(5);
        soccer.addPlayer();
        soccer.printGameStatus();
        soccer.addPlayer();
        basketball.printGameStatus();
        basketball.addPlayer();
        basketball.printGameStatus();
        basketball.addPlayer();
        basketball.addPlayer();
        basketball.addPlayer();
        basketball.printGameStatus();

        // класс Person
        System.out.println(" ");
        Person sasha = new Person("Александр", "Захаров", "123-45-6789");
        Person zhenya = new Person("Евгений", "Маск", "987-65-4321");
        sasha.printPersonInfo();
        zhenya.printPersonInfo();
        sasha.setFirstName("Михаил");
        sasha.printPersonInfo();
    }
}
