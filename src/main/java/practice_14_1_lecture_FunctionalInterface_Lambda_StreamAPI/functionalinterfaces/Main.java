package practice_14_1_lecture_FunctionalInterface_Lambda_StreamAPI.functionalinterfaces;

public class Main {
    public static void main(String[] args) {
         /*MathOperations*/
//        MathOperations add = (a, b) -> a + b;
//        System.out.println(add.operate(5,3));

         /*Predicate*/
//        Predicate<Integer> isMoreThanFive = x -> x > 5;
//        System.out.println(isMoreThanFive.test(4));
//        System.out.println(isMoreThanFive.test(6));

         /*Function*/
//        Function<String, Integer> getLength = str -> str.length();
//        System.out.println(getLength.apply("abc"));

        /*Consumer*/
//        Consumer<String> printer = message -> System.out.println("Сообщение: " + message);
//        printer.accept("Привет, мир!");

        /*Supplier*/
        Supplier<Double> random = Math::random;
        System.out.println(random.get());
    }
}