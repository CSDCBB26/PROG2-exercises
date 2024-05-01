package at.ac.fhcampuswien.fhmdb.movie;

import at.ac.fhcampuswien.fhmdb.BaseTest;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.utils.MovieUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetLongestMovieTitleTest extends BaseTest {

    @Test
    void longest_movie_title_with_all_movies_given_should_return_46() {
        // Star Wars: Episode V - The Empire Strikes Back
        int result = MovieUtils.getLongestMovieTitle(movieList);
        assertEquals(46, result);
    }

    @Test
    void longest_movie_title_with_list_of_movies_containing_The_Godfather_and_Pulp_Fiction_should_return_13() {
        int result = MovieUtils.getLongestMovieTitle(
                List.of(movieMap.get("Pulp Fiction"), movieMap.get("The Godfather"))
        );
        assertEquals(13, result);
    }

    @Test
    void longest_movie_title_with_list_of_movies_containing_The_Shawshank_Redemption_Once_Upon_a_Time_in_Hollywood_12_Angry_Men_should_return_29() {
        int result = MovieUtils.getLongestMovieTitle(
                List.of(movieMap.get("The Shawshank Redemption"),
                        movieMap.get("Once Upon a Time in Hollywood"),
                        movieMap.get("12 Angry Men"))
        );
        assertEquals(29, result);
    }

    @Test
    void longest_movie_title_with_list_containing_a_movie_with_no_title_should_return_0() {
        Movie noTitleMovie = new Movie.Builder()
                .setTitle("")
                .build();
        int result = MovieUtils.getLongestMovieTitle(List.of(noTitleMovie));
        assertEquals(0, result);
    }

    @Test
    void longest_movie_title_with_empty_list_should_return_0() {
        int result = MovieUtils.getLongestMovieTitle(new ArrayList<>());
        assertEquals(0, result);
    }

    @Test
    void longest_movie_title_with_null_list_should_return_0() {
        int result = MovieUtils.getLongestMovieTitle(null);
        assertEquals(0, result);
    }

}