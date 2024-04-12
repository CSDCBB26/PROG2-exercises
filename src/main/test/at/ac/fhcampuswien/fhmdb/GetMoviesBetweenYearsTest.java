package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.utils.MovieUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetMoviesBetweenYearsTest extends BaseTest {

    @Test
    void movies_between_1993_and_1994_should_return_a_list_of_movies_containing_the_movies_The_Shawshank_Redemption_Pulp_Fiction_and_Schindlers_List() {
        List<Movie> result = MovieUtils.getMoviesBetweenYears(movieList, 1993, 1994);
        List<Movie> expected = List.of(
                movieMap.get("The Shawshank Redemption"), movieMap.get("Pulp Fiction"), movieMap.get("Schindler's List")
        );

        assertNotNull(result);
        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    void movies_between_1995_and_1995_should_return_a_list_of_movies_containing_the_movie_Seven() {
        List<Movie> result = MovieUtils.getMoviesBetweenYears(movieList, 1995, 1995);
        List<Movie> expected = List.of(movieMap.get("Seven"));

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void movies_between_1800_and_1850_should_return_an_empty_list() {
        List<Movie> result = MovieUtils.getMoviesBetweenYears(movieList, 1800, 1850);
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void movies_between_1800_and_2024_with_no_release_year_set_should_return_an_empty_list() {
        Movie noReleaseYearMovie = new Movie.Builder()
                .setTitle("No Release Year Movie")
                .build();
        List<Movie> result = MovieUtils.getMoviesBetweenYears(List.of(noReleaseYearMovie), 1800, 2024);
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void movies_between_minus_1_and_0_should_return_an_empty_list() {
        List<Movie> result = MovieUtils.getMoviesBetweenYears(movieList, -1, 0);
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void movies_between_1800_and_2024_with_empty_list_given_should_return_an_empty_list() {
        List<Movie> result = MovieUtils.getMoviesBetweenYears(new ArrayList<>(), 1800, 2024);
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void movies_between_1800_and_2024_with_null_list_given_should_return_an_empty_list() {
        List<Movie> result = MovieUtils.getMoviesBetweenYears(null, 1800, 2024);
        assertEquals(new ArrayList<>(), result);
    }
}