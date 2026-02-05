package practice_1;

public class HelloWorld {
    // поля
    static int a = 1;
    // методы
    // зелёная стрелочка - программа запускаема
    // метод main - точка входа в программу
    public static void main(String[] args) {
        int sum1 = sum(1001, 2000);
        System.out.println("Результат сложения " + sum1);

        int mult1 = multyply(3, 2);
        System.out.println("Результат умножения " + mult1);

        int subs1 = substract(10, 3);
        System.out.println("Результат вычитания " + subs1);

        double div1 = divide(3, 2);
        System.out.println("Результат деления " + div1);
    }
    public static int sum(int x, int y) {
        // тело метода
        return x + y;  // возвращаемое значение из метода
    }
    public static int multyply (int p, int k) { // аргументы метода
        // тело метода
        int mult = p * k; // создал переменную, присвоил значения равное результату умножения p и k
        return mult;
    }
    public static int substract(int g, int l) {
        return g-l;
    }
    // возвращаемый тип данных - целое или нет? 3/2 = 1.5
    public static double divide(int s, int h) {
        return (double) s / h;
    }
}