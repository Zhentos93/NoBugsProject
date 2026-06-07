package practice_14_2_practice_FunctionalInterface_Lambda_StreamAPI.lambda;

public class Main {
    public static void main(String[] args) {
        // анонимный класс
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Привет, мир!");
            }
        };
        r1.run();

        // то же самое, но через лямбда-выражение
        Runnable r2 = () -> System.out.println("Привет, мир!");
        r2.run();
    }
}
