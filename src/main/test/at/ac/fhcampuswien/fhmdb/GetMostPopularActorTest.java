package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.utils.MovieUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetMostPopularActorTest extends BaseTest {

    @Test
    void most_popular_actor_from_all_movies_should_return_Actore_Grande() {
        String result = MovieUtils.getMostPopularActor(movieList);
        assertEquals("Actore Grande", result);
    }

    @Test
    void most_popular_actor_from_a_list_of_movies_containing_The_Godfather_Seven_and_Once_Upon_a_Time_in_Hollywood_should_return_Brad_Pitt() {
        String result = MovieUtils.getMostPopularActor(
                List.of(movieMap.get("The Godfather"), movieMap.get("Once Upon a Time in Hollywood"))
        );
        assertEquals("Actore Grande", result);
    }

    @Test
    void most_popular_actor_with_a_movie_with_no_actors_should_return_empty_string() {
        Movie noActorMovie = new Movie.Builder()
                .setMainCast(new ArrayList<>())
                .build();
        String result = MovieUtils.getMostPopularActor(List.of(noActorMovie));
        assertEquals("", result);
    }

    @Test
    void most_popular_actor_with_empty_list_given_should_return_empty_string() {
        String result = MovieUtils.getMostPopularActor(new ArrayList<>());
        assertEquals("", result);
    }

    @Test
    void most_popular_actor_with_null_list_given_should_return_empty_string() {
        String result = MovieUtils.getMostPopularActor(null);
        assertEquals("", result);
    }
}