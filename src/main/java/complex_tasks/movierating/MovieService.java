package complex_tasks.movierating;

import java.util.*;

public class MovieService {
    private final Map<Movie, List<Rating<Integer>>> ratings = new HashMap<>();

    public synchronized void addRating(Movie movie, Rating<Integer> rating) {

        if (movie == null) {
            throw new IllegalArgumentException("movie cannot be null");
        }

        if (rating == null) {
            throw new IllegalArgumentException("rating cannot be null");
        }

        if (rating.getRating() == null) {
            throw new IllegalArgumentException("rating value cannot be null");
        }

        if (rating.getRating() < 1 || rating.getRating() > 10) {
            throw new IllegalArgumentException("rating must be between 1 and 10");
        }

        List<Rating<Integer>> movieRatings = ratings.get(movie);

        if (movieRatings == null) {
            movieRatings = new ArrayList<>();
            ratings.put(movie, movieRatings);
        }

        movieRatings.add(rating);
    }

    public synchronized double calculateAverageRating(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("movie cannot be null");
        }

        List<Rating<Integer>> movieRatings = ratings.get(movie);

        if (movieRatings == null) {
            throw new IllegalArgumentException("film has no rating");
        }

        return movieRatings.stream()
                .mapToDouble(movieRating -> movieRating.getRating())
                .average()
                .orElseThrow(() -> new IllegalArgumentException("film has no rating"));
    }

    public synchronized List<Movie> sortByAverageRatingDescending() {
        Comparator<Movie> byAverageRating = Comparator.comparingDouble(
                movie -> calculateAverageRating(movie)
        );

        return ratings.keySet().stream()
                .sorted(byAverageRating.reversed())
                .toList();
    }
}
