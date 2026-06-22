package practice_13_1_practice_Exceptions_Generics.library.exceptions;

// непроверяемый -> RuntimeException
public class InvalidBookException extends RuntimeException {
    public InvalidBookException(String message) {
        super(message);
    }
}
