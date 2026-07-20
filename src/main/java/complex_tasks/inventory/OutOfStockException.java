package complex_tasks.inventory;

public class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }
}
