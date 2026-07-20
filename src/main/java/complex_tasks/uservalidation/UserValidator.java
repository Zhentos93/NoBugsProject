package complex_tasks.uservalidation;

public class UserValidator {

    private static boolean validationEnabled = true;

    public static void setValidationEnabled(boolean enabled) {
        UserValidator.validationEnabled = enabled;
    }

    public static boolean validate(User user) throws InvalidUserException {

        if (!validationEnabled) {
            return true;
        }

        if (user == null) {
            throw new InvalidUserException(
                    "User must not be empty"
            );
        }

        if (!isValidName(user.getName())) {
            throw new InvalidUserException(
                    "Invalid name"
            );
        }

        if (!isValidAge(user.getAge())) {
            throw new InvalidUserException(
                    "Invalid age"
            );
        }

        if (!isValidEmail(user.getEmail())) {
            throw new InvalidUserException(
                    "Invalid email"
            );
        }

        return true;
    }

    public static boolean isValidName(String name) {
        return name != null && !name.isBlank() && Character.isUpperCase(name.charAt(0));
    }

    public static boolean isValidAge(int age) {
        return age >= 18 && age <= 100;
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches(
                "^[A-Za-z0-9]+([._-][A-Za-z0-9]+)*@[A-Za-z0-9]+([.-][A-Za-z0-9]+)*\\.[A-Za-z]{2,}$");
    }
}
