package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.utils.MovieUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieSearchTest {

    private List<Movie> movieList;
    private final List<Movie> emptyList = new ArrayList<>();

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
     * Search for movie title / description
     */

    @Test
    void searching_for_superman_should_return_one_movie_superman() {
        List<Movie> result = MovieUtils.search("Superman", movieList);
        List<Movie> expectedMovies = List.of(
                new Movie("Superman", "Description of Superman", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION))
                );
        assertEquals(expectedMovies, result);
    }

    @Test
    void searching_for_wonderwoman_should_return_no_movie() {
        List<Movie> result = MovieUtils.search("Wonderwoman", movieList);
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void searching_for_iceage_should_return_no_movie() {
        List<Movie> result = MovieUtils.search("iceage", movieList);
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void searching_for_3_should_return_spiderman3_and_thor3_movies_from_the_list() {
        List<Movie> result = MovieUtils.search("3", movieList);
        List<Movie> expectedMovies = List.of(
                new Movie("Thor: Love and Thunder", "Thor 3 ", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY, Genre.SCIENCE_FICTION)),
                new Movie("Spiderman: No Way Home", "Spiderman 3", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION))
        );
        assertEquals(expectedMovies, result);
    }

    @Test
    void searching_batman_should_return_4_movies() {
        List<Movie> result = MovieUtils.search("batman", movieList);
        List<Movie> expectedMovies = List.of(
                new Movie("Batman Begins", "Batman the beginning", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.SCIENCE_FICTION)),
                new Movie("The Dark Knight", "Batman the Dark Knight", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("The Dark Knight Rises", "Batman the Dark Knight Rises", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("The Dark Knight Returns", "Batman the Dark Knight Returns", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.ANIMATION))
        );

        assertEquals(expectedMovies, result);
    }

    @Test
    void searching_for_dark_should_return_3_movies() {
        List<Movie> result = MovieUtils.search("dark", movieList);
        List<Movie> expectedMovies = List.of(
                new Movie("The Dark Knight", "Batman the Dark Knight", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("The Dark Knight Rises", "Batman the Dark Knight Rises", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("The Dark Knight Returns", "Batman the Dark Knight Returns", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.ANIMATION))
        );

        assertEquals(expectedMovies, result);
    }

    @Test
    void searching_with_no_string_should_return_all_movies_from_the_list() {
        List<Movie> result = MovieUtils.search("", movieList);
        assertEquals(movieList, result);
    }

    @Test
    void searching_for_whitespace_only_should_return_all_movies_containing_at_least_one_word_in_title_or_description() {
        List<Movie> result = MovieUtils.search(" ", movieList);
        assertEquals(movieList, result);
    }

    @Test
    void searching_for_description_using_the_query_description_of_should_return_movie_superman() {
        List<Movie> result = MovieUtils.search("description of", movieList);
        List<Movie> expectedMovies = List.of(
                new Movie("Superman", "Description of Superman", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION))
        );

        assertEquals(expectedMovies, result);
    }

    @Test
    void searching_for_description_using_the_query_batman_the_should_return_a_list_containing_4_movies() {
        List<Movie> result = MovieUtils.search("batman the", movieList);
        List<Movie> expectedMovies = List.of(
                new Movie("Batman Begins", "Batman the beginning", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.SCIENCE_FICTION)),
                new Movie("The Dark Knight", "Batman the Dark Knight", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("The Dark Knight Rises", "Batman the Dark Knight Rises", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("The Dark Knight Returns", "Batman the Dark Knight Returns", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.ANIMATION))
        );

        assertEquals(expectedMovies, result);
    }
}