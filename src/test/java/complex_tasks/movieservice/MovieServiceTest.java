package complex_tasks.movieservice;

import complex_tasks.movierating.Movie;
import complex_tasks.movierating.MovieService;
import complex_tasks.movierating.Rating;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieServiceTest {
    /**
     * Тесты сервиса рейтингов фильмов:
     * positive cases:
     *  Добавление валидной оценки:
     *          addRating(Movie("Interstellar"), Rating<Integer>(10)) → оценка добавлена
     *  Расчёт средней оценки фильма:
     *      addRating(Movie("Matrix"), Rating<Integer>(10))
     *      addRating(Movie("Matrix"), Rating<Integer>(7))
     *          calculateAverageRating(Movie("Matrix")) → 8.5
     *  Сортировка фильмов по средней оценке по убыванию:
     *      Movie("Matrix"): 10, 8 → average 9.0
     *      Movie("Dune"): 7, 8 → average 7.5
     *      Movie("Avatar"): 6, 10 → average 8.0
     *          getMoviesSortedByAverageRatingDesc() → Matrix, Avatar, Dune
     * corner cases:
     *  Добавление оценки. Минимально допустимая оценка:
     *          addRating(Movie("Interstellar"), Rating<Integer>(1)) → оценка добавлена
     *  Добавление оценки. Максимально допустимая оценка:
     *          addRating(Movie("Interstellar"), Rating<Integer>(10)) → оценка добавлена
     * negative cases:
     *  Добавление оценки. Movie равен null:
     *          addRating(null, Rating<Integer>(8)) → IllegalArgumentException("movie cannot be null")
     *  Добавление оценки. Rating равен null:
     *          addRating(Movie("Interstellar"), null) → IllegalArgumentException("rating cannot be null")
     *  Добавление оценки. Значение рейтинга равно null:
     *          addRating(Movie("Interstellar"), Rating<Integer>(null)) → IllegalArgumentException("rating value cannot be null")
     *  Добавление оценки. Рейтинг меньше допустимого диапазона:
     *          addRating(Movie("Interstellar"), Rating<Integer>(0)) → IllegalArgumentException("rating must be between 1 and 10")
     *  Добавление оценки. Рейтинг больше допустимого диапазона:
     *          addRating(Movie("Interstellar"), Rating<Integer>(11)) → IllegalArgumentException("rating must be between 1 and 10")
     *  Расчёт средней оценки. Movie равен null:
     *          calculateAverageRating(null) → IllegalArgumentException("movie cannot be null")
     *  Расчёт средней оценки. Rating равен null:
     *          calculateAverageRating(Movie("Matrix")) → IllegalArgumentException("movie has no ratings")
     */

    /*addRating() - тесты на добавление рейтингов*/

    @Test
    public void shouldAddValidRating() {
        // Добавление валидной оценки
        MovieService movieService = new MovieService();
        Movie movie = new Movie("Interstellar");

        movieService.addRating(movie, new Rating<>(10));

        double actualResult = movieService.calculateAverageRating(movie);

        assertEquals(10.0, actualResult);
    }

    @ParameterizedTest
    @ValueSource(ints = {
            // Добавление оценки. Минимально допустимая оценка
            1,

            // Добавление оценки. Максимально допустимая оценка
            10
    })
    public void shouldAddRatingWithBoundaryValues(int ratingValue) {
        MovieService movieService = new MovieService();
        Movie movie = new Movie("Interstellar");

        movieService.addRating(movie, new Rating<>(ratingValue));

        double actualResult = movieService.calculateAverageRating(movie);

        assertEquals(ratingValue, actualResult);
    }

    @Test
    public void shouldThrowInvalidMovieExceptionForNullMovie() {
        // Добавление оценки. Movie равен null
        MovieService movieService = new MovieService();

        assertThrows(
                IllegalArgumentException.class,
                () -> movieService.addRating(null, new Rating<>(8))
        );
    }

    @Test
    public void shouldThrowInvalidRatingExceptionForNullRating() {
        // Добавление оценки. Rating равен null
        MovieService movieService = new MovieService();
        Movie movie = new Movie("Interstellar");

        assertThrows(
                IllegalArgumentException.class,
                () -> movieService.addRating(movie, null)
        );
    }

    @Test
    public void shouldThrowInvalidRatingExceptionForNullRatingValue() {
        // Добавление оценки. Значение рейтинга равно null
        MovieService movieService = new MovieService();
        Movie movie = new Movie("Interstellar");

        assertThrows(
                IllegalArgumentException.class,
                () -> movieService.addRating(movie, new Rating<>(null))
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {
            // Добавление оценки. Рейтинг меньше допустимого диапазона
            0,

            // Добавление оценки. Рейтинг больше допустимого диапазона
            11
    })
    public void shouldThrowInvalidRatingExceptionForRatingValueOutOfRange(int ratingValue) {
        MovieService movieService = new MovieService();
        Movie movie = new Movie("Interstellar");

        assertThrows(
                IllegalArgumentException.class,
                () -> movieService.addRating(movie, new Rating<>(ratingValue))
        );
    }

    /*calculateAverageRating() - тесты на расчёт средней оценки*/

    @Test
    public void shouldCalculateAverageRatingForMovie() {
        // Расчёт средней оценки фильма
        MovieService movieService = new MovieService();
        Movie movie = new Movie("Matrix");

        movieService.addRating(movie, new Rating<>(10));
        movieService.addRating(movie, new Rating<>(7));

        double actualResult = movieService.calculateAverageRating(movie);

        assertEquals(8.5, actualResult);
    }

    @Test
    public void shouldThrowExceptionForMovieWithoutRatings() {
        // Расчёт средней оценки. Movie равен null
        MovieService movieService = new MovieService();

        assertThrows(
                IllegalArgumentException.class,
                () -> movieService.calculateAverageRating(null)
        );
    }

    @Test
    public void shouldThrowInvalidRatingExceptionWhenCalculatingAverageForNullRating() {
        // Расчёт средней оценки. Rating равен null
        MovieService movieService = new MovieService();
        Movie movie = new Movie("Matrix");

        assertThrows(
                IllegalArgumentException.class,
                () -> movieService.calculateAverageRating(movie)
        );
    }

    /*sortByAverageRatingDescending() - тесты на сортировку фильмов по убыванию средней оценки*/

    @Test
    public void shouldSortMoviesByAverageRatingDescending() {
        // Сортировка фильмов по средней оценке по убыванию
        MovieService movieService = new MovieService();

        Movie matrix = new Movie("Matrix");
        Movie dune = new Movie("Dune");
        Movie avatar = new Movie("Avatar");

        movieService.addRating(matrix, new Rating<>(10));
        movieService.addRating(matrix, new Rating<>(8));

        movieService.addRating(dune, new Rating<>(7));
        movieService.addRating(dune, new Rating<>(8));

        movieService.addRating(avatar, new Rating<>(6));
        movieService.addRating(avatar, new Rating<>(10));

        List<Movie> actualResult = movieService.sortByAverageRatingDescending();

        assertEquals(List.of(matrix, avatar, dune), actualResult);
    }
}
