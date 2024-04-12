package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.utils.MovieUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieFilterTest extends BaseTest {

    @Test
    void filter_for_horror_should_return_no_movie(){
        List<Movie> actual = MovieUtils.filter(Genre.HORROR,null,0,0);
        List<Movie> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    void filter_for_action_should_return_6_movies(){
        List<Movie> actual = MovieUtils.filter(Genre.ACTION,null,0,0);
        List<Movie> expected = movieListAll.stream().filter(movie->movie.getGenres().contains(Genre.ACTION)).toList();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    void filter_for_action_with_query_super_should_return_no_movie(){
        List<Movie> actual = MovieUtils.filter(Genre.ACTION,"super",0,0);
        List<Movie> expected = movieListAll.stream().filter(movie->
            movie.getGenres().contains(Genre.ACTION) && (
                    movie.getTitle().toLowerCase().contains("super") ||
                    movie.getDescription().toLowerCase().contains("super"))
        ).toList();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    void filter_for_biography_with_query_wolf_should_return_1_movie(){
        List<Movie> actual = MovieUtils.filter(Genre.BIOGRAPHY,"wolf",0,0);
        List<Movie> expected = movieListAll.stream().filter(movie->
                movie.getGenres().contains(Genre.BIOGRAPHY) && (
                    movie.getTitle().toLowerCase().contains("wolf") ||
                    movie.getDescription().toLowerCase().contains("wolf"))
        ).toList();
         assertEquals(expected.size(), actual.size());
    }

    @Test
    void filter_for_biography_with_query_wOlf_should_return_1_movie(){
        List<Movie> actual = MovieUtils.filter(Genre.BIOGRAPHY,"wOlf",0,0);
        List<Movie> expected = movieListAll.stream().filter(movie->
                movie.getGenres().contains(Genre.BIOGRAPHY) && (
                    movie.getTitle().toLowerCase().contains("wolf") ||
                    movie.getDescription().toLowerCase().contains("wolf"))
        ).toList();
        assertEquals(actual.size(), expected.size());
    }

    /**
     * The release year of movies in movielistall is always 0 !!!
     * in movielist there are not all the movies, so filtering those for testing also does not work
     * therefore I use initializeMovies() for the list
     */
    @Test
    void filter_for_release_1994_should_return_4_movies(){
        List<Movie> actual = MovieUtils.filter(null,null,1994,0);
        List<Movie> expected = Movie.initializeMovies().stream().filter(movie -> movie.getReleaseYear() == 1994).toList();
        assertEquals(expected.size(), actual.size());
    }


    @Test
    void filter_for_action_query_empire_release_1980_rating_8_should_return_1_movie(){
        List<Movie> actual = MovieUtils.filter(Genre.ACTION, "empire", 1980, 8);
        List<Movie> expected = movieList.stream().filter(movie ->
                movie.getGenres().contains(Genre.ACTION) &&
                movie.getTitle().toLowerCase().contains("empire") &&
                movie.getReleaseYear() == 1980 &&
                movie.getRating() >= 8
        ).toList();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    void filter_for_rating_9_should_return_3_movies(){
        List<Movie> actual = MovieUtils.filter(null, null, 0, 9);
        List<Movie> expected = movieList.stream().filter(movie -> movie.getRating() >= 9).toList();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    void filter_for_crime_rating_7_should_return_9_movies(){
        List<Movie> actual = MovieUtils.filter(Genre.CRIME, null, 0, 7);
        List<Movie> expected = Movie.initializeMovies().stream().filter(movie ->
                movie.getGenres().contains(Genre.CRIME) &&
                movie.getRating() >= 7
        ).toList();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    void filter_with_no_parameters_should_return_whole_list_of_31_movies(){
        List<Movie> actual = MovieUtils.filter(null,null,0,0);
        List<Movie> expected = Movie.initializeMovies();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    void filter_with_query_lord_should_return_2_movies(){
        List<Movie> actual = MovieUtils.filter(null,"lord",0,0);
        List<Movie> expected = Movie.initializeMovies().stream().filter(movie ->
                movie.getTitle().toLowerCase().contains("lord")).toList();
        assertEquals(expected.size(), actual.size());
    }
}