package practice_13_Exceptions_Generics.exceptions.unchecked;

public class NullPointerExceptionExample {
    public static void main(String[] args) {
        try {
            String text = null;
            int length = text.length(); // Это вызовет NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Попытка вызова метода у null объекта.");
        }
    }
}
