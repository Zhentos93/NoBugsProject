package complex_tasks.uservalidation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTest {
    /**
     * Тесты валидации пользователя:
     * positive cases:
     *  Валидный пользователь:
     *      ("Alena", 25, "alena@gmail.com") → true
     *  Валидный email с допустимыми разделителями:
     *      ("John", 25, "john.doe_1-test@example-domain.com") → true
     * corner cases:
     *  Минимально и максимально допустимый возраст:
     *      ("Alex", 18, "alex@gmail.com") → true
     *      ("Alex", 100, "alex@gmail.com") → true
     *  Имя из одной заглавной буквы:
     *      ("A", 25, "alex@gmail.com") → true
     *  Минимальный допустимый формат email:
     *      ("Alex", 25, "a@b.co") → true
     *  Валидация отключена для невалидных данных:
     *      validationEnabled = false
     *      (null, 17, null) → true
     * negative cases:
     *  User равен null:
     *      null → InvalidUserException("User must not be null")
     *  Имя равно null, пустое или пробельное:
     *      (null, 25, "alex@gmail.com") → InvalidUserException("Invalid name")
     *      ("", 25, "alex@gmail.com") → InvalidUserException("Invalid name")
     *      ("   ", 25, "alex@gmail.com") → InvalidUserException("Invalid name")
     *  Имя начинается со строчной буквы:
     *      ("alex", 25, "alex@gmail.com") → InvalidUserException("Invalid name")
     *  Возраст находится за границами диапазона:
     *      ("Alex", 17, "alex@gmail.com") → InvalidUserException("Invalid age")
     *      ("Alex", 101, "alex@gmail.com") → InvalidUserException("Invalid age")
     *  Email равен null:
     *      ("Alex", 25, null) → InvalidUserException("Invalid email")
     *  Email имеет неправильный формат:
     *      ("Alex", 25, "alexexample.com") → InvalidUserException("Invalid email")
     *      ("Alex", 25, "alex..test@example.com") → InvalidUserException("Invalid email")
     *      ("Alex", 25, "alex@example.c") → InvalidUserException("Invalid email")
     */

    @BeforeEach
    void enableValidation() {
        UserValidator.setValidationEnabled(true);
    }

    @AfterEach
    void resetValidation() {
        UserValidator.setValidationEnabled(true);
    }

    private static Stream<Arguments> validUsers() {
        return Stream.of(
                // positive cases
                // Валидный пользователь
                Arguments.of(new User("Alena", 25, "alena@gmail.com")),
                // Валидный email с допустимыми разделителями
                Arguments.of(new User("John", 25, "john.doe_1-test@example-domain.com")),

                // corner cases
                // Минимально и максимально допустимый возраст
                Arguments.of(new User("Alex", 18, "alex@gmail.com")),
                Arguments.of(new User("Alex", 100, "alex@gmail.com")),
                // Имя из одной заглавной буквы
                Arguments.of(new User("A", 25, "alex@gmail.com")),
                // Минимальный допустимый формат email
                Arguments.of(new User("Alex", 25, "a@b.co"))
        );
    }

    @ParameterizedTest
    @MethodSource("validUsers")
    public void shouldReturnTrueForValidUserData(User user) throws InvalidUserException {

        boolean actualResult = UserValidator.validate(user);

        assertTrue(actualResult);
    }

    @Test
    public void shouldReturnTrueWhenValidationIsDisabled() throws InvalidUserException {
        UserValidator.setValidationEnabled(false);

        // Валидация отключена для невалидных данных
        User user = new User(null, 17, null);

        boolean actualResult = UserValidator.validate(user);

        assertTrue(actualResult);
    }

    @Test
    public void shouldThrowExceptionForNullUser() {
        // User равен null
        assertThrows(
                InvalidUserException.class,
                () -> UserValidator.validate(null),
                "User must not be null"
        );
    }

    private static Stream<Arguments> usersWithInvalidNames() {
        return Stream.of(
                // negative cases
                // Имя равно null, пустое или пробельное
                Arguments.of(new User(null, 25, "alex@gmail.com")),
                Arguments.of(new User("", 25, "alex@gmail.com")),
                Arguments.of(new User("   ", 18, "alex@gmail.com")),
                // Имя начинается со строчной буквы
                Arguments.of(new User("alex", 18, "alex@gmail.com"))
        );
    }

    @ParameterizedTest
    @MethodSource("usersWithInvalidNames")
    public void shouldThrowInvalidUserExceptionForInvalidName(User user) {
        assertThrows(
                InvalidUserException.class,
                () -> UserValidator.validate(user),
                "Invalid name"
        );
    }

    private static Stream<Arguments> usersWithInvalidAges() {
        return Stream.of(
                // negative cases
                // Возраст находится за границами диапазона
                Arguments.of(new User("Alex", 17, "alex@gmail.com")),
                Arguments.of(new User("Alex", 101, "alex@gmail.com"))
        );
    }

    @ParameterizedTest
    @MethodSource("usersWithInvalidAges")
    public void shouldThrowInvalidUserExceptionForInvalidAge(User user) {
        assertThrows(
                InvalidUserException.class,
                () -> UserValidator.validate(user),
                "Invalid age"
        );
    }

    private static Stream<Arguments> usersWithInvalidEmails() {
        return Stream.of(
                // negative cases
                // Email равен null
                Arguments.of(new User("Alex", 25, null)),
                // Email имеет неправильный формат
                Arguments.of(new User("Alex", 25, "alexexample.com")),
                Arguments.of(new User("Alex", 25, "alex..test@example.com")),
                Arguments.of(new User("Alex", 25, "alex@example.c"))
        );
    }

    @ParameterizedTest
    @MethodSource("usersWithInvalidEmails")
    public void shouldThrowInvalidUserExceptionForInvalidEmail(User user) {
        assertThrows(
                InvalidUserException.class,
                () -> UserValidator.validate(user),
                "Invalid email"
        );
    }
}
