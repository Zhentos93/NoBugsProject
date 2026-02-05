package practice_1;

public class MathOperations {
    //методы
    public static void main(String[] args) {
        //1
        int add1 = add(4,5);
        System.out.println("сумма 2-х целых чисел " + add1);
        int subtract1 = subtract(6, 7);
        System.out.println("разница 2-х целых чисел " + subtract1);
        int multiply1 = multiply(3, 7);
        System.out.println("произведение 2-х целых чисел " + multiply1);
        double divide1 = divide(2,3);
        System.out.println("деление 2-х чисел " + divide1);
        //2
        int findMax1 = findMax(5,6);
        System.out.println("нахождение максимума из двух чисел " + findMax1);
        //3
        int difference1 = difference(-4, -5);
        System.out.println("нахождение разницы в шаге между 2-мя числами " + difference1);
        //4
        int squareArea1 = squareArea(6);
        System.out.println("метод для вычисления площади квадрата " + squareArea1);
        int squarePerimeter1 = squarePerimeter(6);
        System.out.println("метод для вычисления периметра квадрата " + squarePerimeter1);
        //5
        double convertSecondsToMinutes1 = convertSecondsToMinutes(61);
        System.out.println("метод для перевода секунд в минуты " + convertSecondsToMinutes1);
        //6
        double averageSpeed1 = averageSpeed(60.2, 2.22);
        System.out.println("метод для вычисления средней скорости " + averageSpeed1);
        //7
        double findHypotenuse1 = findHypotenuse(5, 2);
        System.out.println("Метод для нахождения гипотенузы " + findHypotenuse1);
        //8
        double circleCircumference1 = circleCircumference(7.87);
        System.out.println("Метод для длины окружности " + circleCircumference1);
        //9
        double calculatePercentage1 = calculatePercentage(4.44,2.22);
        System.out.println("Метод для вычисления процентов " + calculatePercentage1);
        //10
        double celsiusToFahrenheit1 = celsiusToFahrenheit(50);
        System.out.println("Методы перевода температуры в Фаренгейты" + celsiusToFahrenheit1);
        double fahrenheitToCelsius1 = fahrenheitToCelsius(50);
        System.out.println("Методы перевода температуры в Цельсий " + fahrenheitToCelsius1);
    }
    //1
    public static int add (int x, int y) {
        //тело метода
        return x + y; //возвращаемое значение метода
    }
    public static int subtract (int x, int y) {
        return x - y;
    }
    public static int multiply (int x, int y) {
        return x * y;
    }
    public static double divide (int x, int y) {
        return (double) x / y;
    }
    //2
    public static int findMax (int x, int y) {
        return Math.max(x, y);
    }
    //3
    public static int difference (int x, int y) {
        return Math.abs(x - y);
    }
    //4
    public static int squareArea (int side) {
        int Area = side * side;
        return Area;
    }
    public static int squarePerimeter (int side) {
        return 4 * side;
    }
    //5
    public static double convertSecondsToMinutes (int seconds) {
        return (double) seconds/60;
    }
    //6
    public static double averageSpeed (double distance, double time) {
        double averageSpeed = distance / time;
        return (double) averageSpeed;
    }
    //7
    public static double findHypotenuse (double a, double b) {
        return (double) Math.sqrt(a*a + b*b);
    }
    //8
    public static double circleCircumference (double radius) {
        double circleCircumference = 2 * Math.PI * radius;
        return (double) circleCircumference;
    }
    //9
    public static double calculatePercentage (double total, double part) {
        return (part / total) * 100;
    }
    //10
    public static double celsiusToFahrenheit (double c) {
        return (double) c * 9 / 5 + 32;
    }
    public static double fahrenheitToCelsius (double f) {
        return (double) (f - 32) * 5 / 9;
    }
}

// Комментарии к ДЗ

//1. Что уже классно — ты реализовал сразу 10 математических задач с хорошим покрытием тем: арифметика, геометрия, преобразования единиц, работа с процентами
//и температурой. Отлично, что ты используешь `Math.abs`, `Math.max`, `Math.sqrt`, а также не забываешь про приведение к `double`, где нужно — особенно в делении.
//Вызовы методов в `main()` демонстрируют понимание, как использовать свои методы на практике. Это очень хорошее и содержательное задание.

// 2. Что можно улучшить — имена переменных вроде `averageSpeed`, `circleCircumference`, `Area` можно убрать (в методах), и сразу писать `return ...`, чтобы не плодить
//лишние переменные без смысла. Также приведение к `double` в некоторых местах лишнее: `return (double) averageSpeed;` — `averageSpeed` и так уже `double`.
//Такие избыточности не вредны, но делают код менее аккуратным.
//И напоследок: можно выровнять стиль вывода в `main()` — сообщения чуть-чуть хаотичны, например: `"Методы перевода температуры в Фаренгейты"` —
//лучше `Метод перевода температуры в Фаренгейты`.