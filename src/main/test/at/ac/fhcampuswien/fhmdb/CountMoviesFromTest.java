package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.utils.MovieUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountMoviesFromTest extends BaseTest {

    @Test
    void counting_movies_from_director_Directore_Grande_should_return_3() {
        long result = MovieUtils.countMoviesFrom(movieList, "Directore Grande");
        assertEquals(3, result);
    }

    @Test
    void counting_movies_from_director_Quentin_Tarantino_should_return_2() {
        long result = MovieUtils.countMoviesFrom(movieList, "Quentin Tarantino");
        assertEquals(2, result);
    }

    @Test
    void counting_movies_from_director_qUeNtin_taRantinO_should_return_2() {
        long result = MovieUtils.countMoviesFrom(movieList, "qUeNtin taRantinO");
        assertEquals(2, result);
    }

    @Test
    void counting_movies_from_director_QUENTIN_TARANTINO_should_return_2() {
        long result = MovieUtils.countMoviesFrom(movieList, "QUENTIN TARANTINO");
        assertEquals(2, result);
    }

    @Test
    void counting_movies_from_director_Frank_Darabont_should_return_1() {
        long result = MovieUtils.countMoviesFrom(movieList, "Frank Darabont");
        assertEquals(1, result);
    }

    @Test
    void counting_movies_from_director_Peter_Jackson_should_return_1() {
        long result = MovieUtils.countMoviesFrom(movieList, "Peter Jackson");
        assertEquals(1, result);
    }

    @Test
    void counting_movies_from_director_Alfred_Hitchcock_should_return_0() {
        long result = MovieUtils.countMoviesFrom(movieList, "Alfred Hitchcock");
        assertEquals(0, result);
    }

    @Test
    void counting_movies_with_blank_string_as_director_name_should_return_0() {
        long result = MovieUtils.countMoviesFrom(movieList, " ");
        assertEquals(0, result);
    }

    @Test
    void counting_movies_with_empty_string_as_director_name_should_return_0() {
        long result = MovieUtils.countMoviesFrom(movieList, "");
        assertEquals(0, result);
    }

    @Test
    void counting_movies_from_director_Tarantino_should_return_0() {
        long result = MovieUtils.countMoviesFrom(movieList, "Tarantino");
        assertEquals(0, result);
    }

    @Test
    void counting_movies_with_null_movies_and_director_should_return_0() {
        long result = MovieUtils.countMoviesFrom(null, null);
        assertEquals(0, result);
    }

    @Test
    void counting_movies_with_null_movies_should_return_0() {
        long result = MovieUtils.countMoviesFrom(null, "Peter Jackson");
        assertEquals(0, result);
    }

    @Test
    void counting_movies_with_null_director_should_return_0() {
        long result = MovieUtils.countMoviesFrom(movieList, null);
        assertEquals(0, result);
    }
}