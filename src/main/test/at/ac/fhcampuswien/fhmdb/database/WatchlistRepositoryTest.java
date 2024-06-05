package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.BaseTest;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import com.j256.ormlite.dao.DaoManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WatchlistRepositoryTest extends BaseTest {

    private WatchlistRepository watchlistRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        super.setUp();
        watchlistRepository = WatchlistRepository.getWatchlistRepository(DaoManager.createDao(connectionSource, WatchlistMovieEntity.class));
    }

    @Test
    public void after_adding_movies_to_watchlist_should_be_able_to_retrieve_them() throws DatabaseException {
        WatchlistMovieEntity movie1 = new WatchlistMovieEntity("api1");
        WatchlistMovieEntity movie2 = new WatchlistMovieEntity("api2");

        watchlistRepository.addToWatchlist(movie1);
        watchlistRepository.addToWatchlist(movie2);

        List<WatchlistMovieEntity> retrievedWatchlist = watchlistRepository.getWatchlist();
        assertEquals(2, retrievedWatchlist.size());
        assertTrue(retrievedWatchlist.contains(movie1));
        assertTrue(retrievedWatchlist.contains(movie2));
    }

    @Test
    public void removing_all_movies_from_watchlist_should_delete_all_records_from_watchlist_table() throws DatabaseException {
        WatchlistMovieEntity movie1 = new WatchlistMovieEntity("api1");
        WatchlistMovieEntity movie2 = new WatchlistMovieEntity("api2");

        watchlistRepository.addToWatchlist(movie1);
        watchlistRepository.addToWatchlist(movie2);

        watchlistRepository.removeFromWatchlist("api1");
        watchlistRepository.removeFromWatchlist("api2");

        List<WatchlistMovieEntity> retrievedWatchlist = watchlistRepository.getWatchlist();
        assertTrue(retrievedWatchlist.isEmpty());
    }

    @Test
    public void adding_movie_with_missing_required_fields_to_watchlist_should_throw_DatabaseException() {
        WatchlistMovieEntity invalidMovie = new WatchlistMovieEntity();
        assertThrows(DatabaseException.class, () -> watchlistRepository.addToWatchlist(invalidMovie));
    }

    @Test
    public void getting_movie_from_watchlist_with_id_should_return_a_movie_from_watchlist_table() throws DatabaseException {
        WatchlistMovieEntity movie1 = new WatchlistMovieEntity("api1");
        WatchlistMovieEntity movie2 = new WatchlistMovieEntity("api2");

        watchlistRepository.addToWatchlist(movie1);
        watchlistRepository.addToWatchlist(movie2);

        List<WatchlistMovieEntity> retrievedWatchlist = watchlistRepository.getWatchlist();
        WatchlistMovieEntity retrievedMovie = retrievedWatchlist.get(0);

        assertNotNull(retrievedMovie);
        assertEquals(movie1.getApiId(), retrievedMovie.getApiId());
        assertEquals(movie1.getApiId(), retrievedMovie.getApiId());
    }

    @Test
    public void getting_movie_from_watchlist_with_non_existing_id_should_return_null() throws DatabaseException {
        assertNull(watchlistRepository.getFromWatchlist("99999L"));
    }
}