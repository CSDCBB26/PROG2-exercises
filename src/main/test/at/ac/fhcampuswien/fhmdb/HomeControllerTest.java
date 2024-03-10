package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HomeControllerTest {

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
    void searching_for_superman_should_return_movie_superman() {
        List<Movie> result = HomeController.search("Superman", movieList);
        assertEquals(1, result.size());

    }

    @Test
    void searching_for_wonderwoman_should_return_no_movie() {
        List<Movie> result = HomeController.search("Wonderwoman", movieList);
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void searching_for_iceage_should_return_no_movie() {
        List<Movie> result = HomeController.search("iceage", movieList);
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void searching_batman_should_return_4_movies() {
        List<Movie> result = HomeController.search("batman", movieList);
        List<Movie> expectedMovies = List.of(
                new Movie("Batman Begins", "Batman the beginning", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.SCIENCE_FICTION)),
                new Movie("The Dark Knight", "Batman the Dark Knight", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("The Dark Knight Rises", "Batman the Dark Knight Rises", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("The Dark Knight Returns", "Batman the Dark Knight Returns", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.ANIMATION))
        );

        assertEquals(expectedMovies, result);
    }
    @Test
    void searching_dark_should_return_3_movies() {
        List<Movie> result = HomeController.search("dark", movieList);
        List<Movie> expectedMovies = List.of(
                new Movie("The Dark Knight", "Batman the Dark Knight", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("The Dark Knight Rises", "Batman the Dark Knight Rises", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("The Dark Knight Returns", "Batman the Dark Knight Returns", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.ANIMATION))
        );

        assertEquals(expectedMovies, result);
    }

    @Test
    void searching_for_whitespace_only_should_return_all_movies_containing_at_least_one_word_in_title_or_description() {
        List<Movie> result = HomeController.search(" ", movieList);
        assertEquals(11, result.size());
    }

    @Test
    void searching_for_description_using_the_query_description_of_should_return_movie_superman() {
        List<Movie> result = HomeController.search("description of", movieList);
        List<Movie> expectedMovies = List.of(
                new Movie("Superman", "Description of Superman", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION))
        );

        assertEquals(expectedMovies, result);
    }

    @Test
    void searching_for_description_using_the_query_batman_the_should_return_a_list_containing_4_movies() {
        List<Movie> result = HomeController.search("batman the", movieList);
        List<Movie> expectedMovies = List.of(
                new Movie("Batman Begins", "Batman the beginning", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.SCIENCE_FICTION)),
                new Movie("The Dark Knight", "Batman the Dark Knight", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("The Dark Knight Rises", "Batman the Dark Knight Rises", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("The Dark Knight Returns", "Batman the Dark Knight Returns", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.ANIMATION))
        );

        assertEquals(expectedMovies, result);
    }

    /**
     * Filter for movie genre
     */
    @Test
    void filter_for_horror_should_return_no_movie() {
        List<Movie> result = HomeController.filter(Genre.HORROR, movieList, "");

        assertTrue(result.isEmpty());
    }

    @Test
    void filter_for_action_having_SuPeR_as_queryString_should_return_movie_superman() {
        List<Movie> result = HomeController.filter(Genre.ACTION, movieList, "SuPeR");
        List<Movie> expectedMovies = List.of(new Movie("Superman", "Description of Superman", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));

        assertEquals(expectedMovies, result);
    }

    @Test
    void filter_for_action_having_ddd_as_queryString_should_return_no_movie() {
        List<Movie> result = HomeController.filter(Genre.ACTION, movieList, "ddd");

        assertTrue(result.isEmpty());
    }

    @Test
    void filter_for_adventure_having_batman_the_beginning_as_queryString_should_return_movie_BatmanBegins() {
        List<Movie> result = HomeController.filter(Genre.ADVENTURE, movieList, "batman the beginning");
        List<Movie> expectedMovies = List.of(new Movie("Batman Begins", "Batman the beginning", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.SCIENCE_FICTION)));

        assertTrue(result.size() == expectedMovies.size()
                && result.containsAll(expectedMovies)
                && expectedMovies.containsAll(result));
    }

    @Test
    void filter_for_all_genres_having_empty_space_as_queryString_should_return_all_movies() {
        List<Movie> result = HomeController.filter(Genre.ALL, movieList, " ");
        List<Movie> expectedMovies = movieList;

        assertTrue(result.size() == expectedMovies.size()
                && result.containsAll(expectedMovies)
                && expectedMovies.containsAll(result));
    }

    @Test
    void filter_for_action_having_tHe_DaRk_as_queryString_should_return_a_list_of_3_movies() {
        List<Movie> result = HomeController.filter(Genre.ACTION, movieList, "tHe DaRk");
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
        List<Movie> result = HomeController.filter(Genre.ACTION, movieList, "");
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
        List<Movie> result = HomeController.filter(Genre.DOCUMENTARY, movieList, "");
        List<Movie> expectedMovies = List.of(new Movie("Documentation about the Universe", "Documentation about the Universe", List.of(Genre.DOCUMENTARY)));

        assertTrue(result.size() == expectedMovies.size()
                && result.containsAll(expectedMovies)
                && expectedMovies.containsAll(result));
    }

    @Test
    void filter_for_adventure_should_return_a_list_containing_8_movies() {
        List<Movie> result = HomeController.filter(Genre.ADVENTURE, movieList, "");
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
        List<Movie> result = HomeController.filter(Genre.CRIME, movieList, "");

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

    /**
     * Sort the movie list
     */
    @Test
    void sort_empty_movielist_ascending(){
        List<Movie> result = HomeController.sort("ascending", emptyList);

        List<Movie> asc_movieList = new ArrayList<>();

        assertEquals(asc_movieList, result);
    }

    @Test
    void sort_empty_movielist_descending(){
        List<Movie> result = HomeController.sort("descending", emptyList);

        List<Movie> asc_movieList = new ArrayList<>();

        assertEquals(asc_movieList, result);
    }

    // TODO - to discuss - no need for toString anymore, since we changed the equals method in movie
    @Test
    void sort_the_movielist_ascending(){
        List<Movie> temp = movieList;
        List<Movie> result = HomeController.sort("ascending", temp);

        List<Movie> asc_movieList = new ArrayList<>();
        asc_movieList.add(new Movie("Batman Begins", "Batman the beginning", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.SCIENCE_FICTION)));
        asc_movieList.add(new Movie("Documentation about the Universe", "Documentation about the Universe", List.of(Genre.DOCUMENTARY)));
        asc_movieList.add(new Movie("Ironman", "Ironman 1", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        asc_movieList.add(new Movie("Peaky Blinders", "Peaky Blinders description", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA)));
        asc_movieList.add(new Movie("Spiderman: No Way Home", "Spiderman 3", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        asc_movieList.add(new Movie("Superman", "Description of Superman", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        asc_movieList.add(new Movie("The Avengers", "The Avengers", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        asc_movieList.add(new Movie("The Dark Knight", "Batman the Dark Knight", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)));
        asc_movieList.add(new Movie("The Dark Knight Returns", "Batman the Dark Knight Returns", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.ANIMATION)));
        asc_movieList.add(new Movie("The Dark Knight Rises", "Batman the Dark Knight Rises", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)));
        asc_movieList.add(new Movie("Thor: Love and Thunder", "Thor 3 ", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY, Genre.SCIENCE_FICTION)));

        String expected = asc_movieList.toString();
        String actual = result.toString();


        assertEquals(expected, actual);

       // assertEquals(asc_movieList, result);
    }

    // TODO - to discuss - no need for toString anymore, since we changed the equals method in movie
    @Test
    void sort_the_movielist_descending(){
        List<Movie> temp = movieList;
        List<Movie> result = HomeController.sort("descending", temp);

        List<Movie> desc_movieList = new ArrayList<>();
        desc_movieList.add(new Movie("Thor: Love and Thunder", "Thor 3 ", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY, Genre.SCIENCE_FICTION)));
        desc_movieList.add(new Movie("The Dark Knight Rises", "Batman the Dark Knight Rises", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)));
        desc_movieList.add(new Movie("The Dark Knight Returns", "Batman the Dark Knight Returns", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.ANIMATION)));
        desc_movieList.add(new Movie("The Dark Knight", "Batman the Dark Knight", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)));
        desc_movieList.add(new Movie("The Avengers", "The Avengers", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        desc_movieList.add(new Movie("Superman", "Description of Superman", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        desc_movieList.add(new Movie("Spiderman: No Way Home", "Spiderman 3", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        desc_movieList.add(new Movie("Peaky Blinders", "Peaky Blinders description", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA)));
        desc_movieList.add(new Movie("Ironman", "Ironman 1", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        desc_movieList.add(new Movie("Documentation about the Universe", "Documentation about the Universe", List.of(Genre.DOCUMENTARY)));
        desc_movieList.add(new Movie("Batman Begins", "Batman the beginning", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.SCIENCE_FICTION)));

        String expected = desc_movieList.toString();
        String actual = result.toString();

        assertEquals(expected, actual);

        //assertEquals(desc_movieList, result);
    }
}