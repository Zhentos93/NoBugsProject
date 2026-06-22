/*Условие задачи:
    Разработайте метод, который проверяет валидность возраста пользователя.
    Если возраст меньше 0 или больше 150, метод должен выбрасывать проверяемое исключение.*/

package practice_13_2_homework_Exceptions_Generics.exceptions.own_checked;

public class Age {

    public void checkAge(int age) throws IncorrectAge {
        if (age < 0 || age > 150) {
            throw new IncorrectAge("такой возраст не подходит");
        }
    }

    public static void main(String[] args) {
        Age age = new Age();
        try {
            age.checkAge(151);
            System.out.println("Подходящий возраст");
        } catch (IncorrectAge e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
