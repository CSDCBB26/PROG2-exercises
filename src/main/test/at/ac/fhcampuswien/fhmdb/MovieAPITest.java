package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.utils.MovieAPI;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieAPITest {

    //ToDo Andi
    @Test
    void API_call_should_return_all_movies() {
        String result = MovieAPI.getAllMovies(MovieAPI.API_URL);
    }

}