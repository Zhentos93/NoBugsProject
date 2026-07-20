package complex_tasks.taskmanager;

public class sdfsdfs {
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
}
