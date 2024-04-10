package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.utils.MovieUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MovieFilterTest {

    private List<Movie> movieList;

    @BeforeEach
    void setUp() {
        movieList = new ArrayList<>();
        movieList.add(new Movie("Superman", "Description of Superman", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        movieList.add(new Movie("Batman Begins", "Batman the beginning", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.SCIENCE_FICTION)));
        movieList.add(new Movie("Peaky Blinders", "Peaky Blinders description", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA)));
        movieList.add(new Movie("Ironman", "Ironman 1", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        movieList.add(new Movie("Thor: Love and Thunder", "Thor 3 ", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY, Genre.SCIENCE_FICTION)));
        movieList.add(new Movie("The Dark Knight", "Batman the Dark Knight", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)));
        movieList.add(new Movie("The Dark Knight Rises", "Batman the Dark Knight Rises", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)));
        movieList.add(new Movie("The Dark Knight Returns", "Batman the Dark Knight Returns", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.ANIMATION)));
        movieList.add(new Movie("Spiderman: No Way Home", "Spiderman 3", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        movieList.add(new Movie("The Avengers", "The Avengers", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        movieList.add(new Movie("Documentation about the Universe", "Documentation about the Universe", List.of(Genre.DOCUMENTARY)));
    }
    
    /**
     * Filter for movie genre
     */
    @Test
    void filter_for_horror_should_return_no_movie() {
        List<Movie> result = MovieUtils.filter(Genre.HORROR, movieList, "");

        assertTrue(result.isEmpty());
    }

    @Test
    void filter_for_action_having_SuPeR_as_queryString_should_return_movie_superman() {
        List<Movie> result = MovieUtils.filter(Genre.ACTION, movieList, "SuPeR");
        List<Movie> expectedMovies = List.of(new Movie("Superman", "Description of Superman", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));

        assertEquals(expectedMovies, result);
    }

    @Test
    void filter_for_action_having_ddd_as_queryString_should_return_no_movie() {
        List<Movie> result = MovieUtils.filter(Genre.ACTION, movieList, "ddd");

        assertTrue(result.isEmpty());
    }

    @Test
    void filter_for_adventure_having_batman_the_beginning_as_queryString_should_return_movie_BatmanBegins() {
        List<Movie> result = MovieUtils.filter(Genre.ADVENTURE, movieList, "batman the beginning");
        List<Movie> expectedMovies = List.of(new Movie("Batman Begins", "Batman the beginning", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.SCIENCE_FICTION)));

        //split to 3 assertions, granualar & better for troubleshooting
        assertTrue(result.size() == expectedMovies.size()
                && result.containsAll(expectedMovies)
                && expectedMovies.containsAll(result));
    }

    @Test
    void filter_for_all_genres_having_empty_space_as_queryString_should_return_all_movies() {
        List<Movie> result = MovieUtils.filter(Genre.ALL, movieList, " ");
        List<Movie> expectedMovies = movieList;

        assertTrue(result.size() == expectedMovies.size()
                && result.containsAll(expectedMovies)
                && expectedMovies.containsAll(result));
    }
    // split methods and tests to more test classes for better structure
    @Test
    void filter_for_action_having_tHe_DaRk_as_queryString_should_return_a_list_of_3_movies() {
        List<Movie> result = MovieUtils.filter(Genre.ACTION, movieList, "tHe DaRk");
        List<Movie> expectedMovies = List.of(
                new Movie("The Dark Knight", "Batman the Dark Knight", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("The Dark Knight Returns", "Batman the Dark Knight Returns", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.ANIMATION)),
                new Movie("The Dark Knight Rises", "Batman the Dark Knight Rises", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER))
        );

        assertTrue(result.size() == expectedMovies.size()
                && result.containsAll(expectedMovies)
                && expectedMovies.containsAll(result));
    }


    @Test
    void filter_for_action_should_return_a_list_of_10_movies() {
        List<Movie> result = MovieUtils.filter(Genre.ACTION, movieList, "");
        List<Movie> expectedMovies = List.of(
                new Movie("Superman", "Description of Superman", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)),
                new Movie("Batman Begins", "Batman the beginning", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.SCIENCE_FICTION)),
                new Movie("Peaky Blinders", "Peaky Blinders description", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA)),
                new Movie("Ironman", "Ironman 1", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)),
                new Movie("Thor: Love and Thunder", "Thor 3 ", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY, Genre.SCIENCE_FICTION)),
                new Movie("The Dark Knight", "Batman the Dark Knight", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("The Dark Knight Rises", "Batman the Dark Knight Rises", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("The Dark Knight Returns", "Batman the Dark Knight Returns", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.ANIMATION)),
                new Movie("Spiderman: No Way Home", "Spiderman 3", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)),
                new Movie("The Avengers", "The Avengers", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION))
        );

        assertTrue(result.size() == expectedMovies.size()
                && result.containsAll(expectedMovies)
                && expectedMovies.containsAll(result));
    }

    @Test
    void filter_for_documentary_should_return_Documentary_about_the_Universe_movie() {
        List<Movie> result = MovieUtils.filter(Genre.DOCUMENTARY, movieList, "");
        List<Movie> expectedMovies = List.of(new Movie("Documentation about the Universe", "Documentation about the Universe", List.of(Genre.DOCUMENTARY)));

        assertEquals(result, expectedMovies);
    }

    @Test
    void filter_for_adventure_should_return_a_list_containing_8_movies() {
        List<Movie> result = MovieUtils.filter(Genre.ADVENTURE, movieList, "");
        List<Movie> expectedMovies = List.of(
                new Movie("Superman", "Description of Superman", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)),
                new Movie("Thor: Love and Thunder", "Thor 3 ", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY, Genre.SCIENCE_FICTION)),
                new Movie("Batman Begins", "Batman the beginning", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.SCIENCE_FICTION)),
                new Movie("Ironman", "Ironman 1", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)),
                new Movie("The Dark Knight Rises", "Batman the Dark Knight Rises", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("The Dark Knight Returns", "Batman the Dark Knight Returns", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.ANIMATION)),
                new Movie("Spiderman: No Way Home", "Spiderman 3", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)),
                new Movie("The Avengers", "The Avengers", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION))
        );

        assertTrue(result.size() == expectedMovies.size() && result.containsAll(expectedMovies) && expectedMovies.containsAll(result));
    }

    @Test
    void filter_for_crime_should_return_5_movies() {
        List<Movie> result = MovieUtils.filter(Genre.CRIME, movieList, "");

        List<Movie> expectedMovies = List.of(
                new Movie("Batman Begins", "Batman the beginning", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.SCIENCE_FICTION)),
                new Movie("Peaky Blinders", "Peaky Blinders description", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA)),
                new Movie("The Dark Knight", "Batman the Dark Knight", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("The Dark Knight Rises", "Batman the Dark Knight Rises", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("The Dark Knight Returns", "Batman the Dark Knight Returns", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.ANIMATION))
        );

        assertTrue(result.size() == expectedMovies.size()
                && result.containsAll(expectedMovies)
                && expectedMovies.containsAll(result));
    }
}