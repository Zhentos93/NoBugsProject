package practice_1;

public class Main { // создаём класс/объявляем класс/определили класс = Class declaration; public - модификатор доступа, class - ключевое слово, practice_1.Main - имя класса в PascalCase
    public static void main(String[] args) { // сигнатура метода main или объявление метода main = method signature
        // объявление переменной - объявление типа и имени (тип = int, имя = count)
        int count; // variable declaration
        count = 0; // инициализация = первое присваивание значения (0) = variable initialization

        // объявляем переменную count2 и инициализируем её значением 0
        int count2 = 0; // variable assignment

        // присвоим новое значение переменной или обновим значение переменной
        count2 = 2; // в уже существующую переменную положили новое значение 2, т.о. мы обновили переменную count2 новым значением 2 = we assigned new value to count 1 variable

        System.out.println(square(4)); // вызов метода с конкретным значением параметра = аргументом = here we called the method 'square' with argument '4' and we printed out the result to the console with new line
    }

    public static int square(int x) { // сигнатура метода и успешно реализовали формулу, по которой подсчитываем квадрат x в теле метода
        // если я хочу, чтобы метод был привязан к классу, а не к объекту (экземпляру класса practice_1.Main, то мы указываем static)
        // тело метода
        return x*x; // return - ключевое слово
    }
}
