package practice_13_Exceptions_Generics.library.exceptions;

// проверяемое -> Exception (наследников)
public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}
