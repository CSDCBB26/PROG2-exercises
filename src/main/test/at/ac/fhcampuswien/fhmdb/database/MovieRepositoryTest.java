package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.BaseTest;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import com.j256.ormlite.dao.DaoManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class MovieRepositoryTest extends BaseTest {

    private MovieRepository movieRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        super.setUp();
        movieRepository = new MovieRepository(DaoManager.createDao(connectionSource, MovieEntity.class));
    }

    @Test
    public void after_adding_movies_in_the_database_should_be_able_to_retrieve_them() throws DatabaseException {
        List<MovieEntity> movieEntities = movieMap.values().stream()
                .map(movie -> new MovieEntity.Builder()
                        .setApiID(movie.getAppID())
                        .setTitle(movie.getTitle())
                        .setDescription(movie.getDescription())
                        .setGenres(movie.getGenres())
                        .setDirectors(movie.getDirectors())
                        .setReleaseYear(movie.getReleaseYear())
                        .setImgUrl(movie.getImgUrl())
                        .setLengthInMinutes(movie.getLengthInMinutes())
                        .setRating(movie.getRating())
                        .build())
                .collect(Collectors.toList());

        movieRepository.addAllMovies(movieEntities);

        List<MovieEntity> retrievedMovies = movieRepository.getAllMovies();
        assertEquals(movieMap.size(), retrievedMovies.size());

        for (MovieEntity movieEntity : movieEntities) {
            assertTrue(retrievedMovies.contains(movieEntity));
        }
    }

    @Test
    public void removing_all_movies_from_the_database_should_delete_all_records_from_movie_table() throws DatabaseException {
        List<MovieEntity> movieEntities = movieMap.values().stream()
                .map(movie -> new MovieEntity.Builder()
                        .setApiID(movie.getAppID())
                        .setTitle(movie.getTitle())
                        .setDescription(movie.getDescription())
                        .setGenres(movie.getGenres())
                        .setDirectors(movie.getDirectors())
                        .setReleaseYear(movie.getReleaseYear())
                        .setImgUrl(movie.getImgUrl())
                        .setLengthInMinutes(movie.getLengthInMinutes())
                        .setRating(movie.getRating())
                        .build())
                .collect(Collectors.toList());

        movieRepository.addAllMovies(movieEntities);

        movieRepository.removeAll();

        List<MovieEntity> retrievedMovies = movieRepository.getAllMovies();
        assertTrue(retrievedMovies.isEmpty());
    }

    @Test
    public void adding_movie_with_missing_required_fields_should_throw_DatabaseException() {
        MovieEntity invalidMovie = new MovieEntity();
        assertThrows(DatabaseException.class, () -> movieRepository.addAllMovies(List.of(invalidMovie)));
    }

    @Test
    public void getting_movie_with_id_0_should_return_the_first_movie_in_the_table() throws DatabaseException {
        List<MovieEntity> movieEntities = movieMap.values().stream()
                .map(movie -> new MovieEntity.Builder()
                        .setApiID(movie.getAppID())
                        .setTitle(movie.getTitle())
                        .setDescription(movie.getDescription())
                        .setGenres(movie.getGenres())
                        .setDirectors(movie.getDirectors())
                        .setReleaseYear(movie.getReleaseYear())
                        .setImgUrl(movie.getImgUrl())
                        .setLengthInMinutes(movie.getLengthInMinutes())
                        .setRating(movie.getRating())
                        .build())
                .collect(Collectors.toList());

        movieRepository.addAllMovies(movieEntities);

        MovieEntity movieEntity = movieEntities.get(0);
        MovieEntity retrievedMovie = movieRepository.getMovie(movieEntity.getId());

        assertNotNull(retrievedMovie);
        assertEquals(movieEntity.getId(), retrievedMovie.getId());
        assertEquals(movieEntity.getTitle(), retrievedMovie.getTitle());
    }

    @Test
    public void getting_a_movie_with_non_existing_id_should_return_a_DatabaseException() {
        assertThrows(DatabaseException.class, () -> movieRepository.getMovie(999L));
    }
}
