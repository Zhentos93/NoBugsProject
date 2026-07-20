package complex_tasks.movierating;

public class Rating<T extends Number> {
    private final T rating;

    public Rating(T rating) {
        this.rating = rating;
    }

    public T getRating() {
        return rating;
    }
}
