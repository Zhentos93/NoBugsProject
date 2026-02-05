package practice_1;

public class UserInfo { // автоматически определяем класс practice_1.UserInfo c модификатором доступа public
    private static final String DEFAULT_NAME = "unknown"; // DEFAULT_NAME написан в стиле Snake_Case
    private String name; // Объявление поля класса с типом String и именем name
    private int age;

    public void printInfo() { // public - модификатор доступа, void - тип данных, т.е. ничего не возвращаем, printInfo - название метода в стиле camelCase
        System.out.println("Имя: " + name); // тело метода // вызов метода по распечатыванию в консоль, в теле передаем конкретный аргумент "Имя: ", конкатенированное с полем name
        System.out.println("Возраст: " + age); // тело метода // вызов метода по распечатыванию в консоль, в теле передаем конкретный аргумент "Возраст: ", конкатенированное с полем age
    }

    public void setName (String name) { // мы создали сеттер setName, всё, что начинается с set - мы так всегда называем сеттеры
        this.name = name; // текущему экземпляру объекта мы присваиваем в поле name новое значение, которое мне передано как параметр с типом данных String и названием параметра name
    }

    public void setAge (int age) { // мы создали сеттер (метод) setAge c типом данных void и c передаваемым параметром age и типом данных int
        this.age = age; // текущему экземпляру объекта мы присваиваем в поле age новое значение, которое мне передано как параметр с типом данных int и названием параметра age
    }

    public static void main(String[] args) { // создать точку входа в наш класс practice_1.UserInfo, а именно метод main (определение метода main - точка входа)

        UserInfo userInfo = new UserInfo(); // объявили перем. userInfo с типом данных practice_1.UserInfo и сразу инициализируем перем. userInfo с помощью оператора new и вызова конструктора по умолчанию
        userInfo.setName("Олег");
        userInfo.setAge(21);
        userInfo.printInfo();
    }
}