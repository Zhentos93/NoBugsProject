/*Условие задачи:
    Напишите функцию, которая принимает строку в качестве аргумента и проверяет,
    является ли строка правильным электронным адресом. Если строка не удовлетворяет критериям,
    функция должна выбрасывать непроверяемое исключение.*/

package practice_13_2_homework_Exceptions_Generics.exceptions.own_unchecked;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

    public void checkEmail(String email) throws InvalidEmail {

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(email);

        boolean isValid = matcher.matches();

        if (isValid) {
            System.out.println("Email валиден");
        } else {
            throw new InvalidEmail("email невалиден");
        }
    }

    public static void main(String[] args) {
        Email email = new Email();
        try {
            email.checkEmail("test@mail.ru");
        } catch (InvalidEmail e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
