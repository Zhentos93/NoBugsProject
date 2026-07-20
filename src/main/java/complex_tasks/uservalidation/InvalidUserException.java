package complex_tasks.uservalidation;

public class InvalidUserException extends Exception {
    public InvalidUserException(String message) {
        super(message);
    }
}
