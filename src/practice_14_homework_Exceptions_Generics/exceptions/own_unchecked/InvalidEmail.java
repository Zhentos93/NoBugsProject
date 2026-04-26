package practice_14_homework_Exceptions_Generics.exceptions.own_unchecked;

public class InvalidEmail extends RuntimeException {
    public InvalidEmail(String message) {
        super(message);
    }
}
