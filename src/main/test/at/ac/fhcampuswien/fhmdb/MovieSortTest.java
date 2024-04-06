package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.utils.MovieUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MovieSortTest {

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
     * Sort the movie list
     */
    @Test
    void sort_empty_movielist_ascending(){
        List<Movie> result = MovieUtils.sort("ascending", emptyList);

        List<Movie> asc_movieList = new ArrayList<>();

        assertEquals(asc_movieList, result);
    }

    @Test
    void sort_empty_movielist_descending(){
        List<Movie> result = MovieUtils.sort("descending", emptyList);

        List<Movie> asc_movieList = new ArrayList<>();

        assertEquals(asc_movieList, result);
    }

    @Test
    void sort_the_movielist_ascending(){
        List<Movie> temp = movieList;
        List<Movie> result = MovieUtils.sort("ascending", temp);

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

        assertEquals(asc_movieList, result);
    }

    @Test
    void sort_the_movielist_descending(){
        List<Movie> temp = movieList;
        List<Movie> result = MovieUtils.sort("descending", temp);

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

        assertEquals(desc_movieList, result);
    }
}