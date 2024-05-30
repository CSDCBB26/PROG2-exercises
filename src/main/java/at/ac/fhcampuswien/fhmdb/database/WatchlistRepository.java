package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;

public class WatchlistRepository {

    private Dao<WatchlistMovieEntity, Long> dao;

    public WatchlistRepository() {
        this.dao = DatabaseManager.getDatabaseInstance().getWatchlistMovieDao();
    }

    public WatchlistRepository(Dao<WatchlistMovieEntity, Long> dao) {
        this.dao = dao;
    }

    public List<WatchlistMovieEntity> getWatchlist() throws DatabaseException {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve the watchlist", e);
        }
    }

    public WatchlistMovieEntity getFromWatchlist(String apiID) throws DatabaseException {
        try {
            // Use the queryForEq method to search for apiId
            List<WatchlistMovieEntity> result = dao.queryForEq("apiId", apiID);
            if (result != null && !result.isEmpty()) {
                return result.get(0);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve the movie from watchlist with apiId " + apiID, e);
        }
    }


    public int addToWatchlist(WatchlistMovieEntity movie) throws DatabaseException {
        try {
            return dao.createOrUpdate(movie).getNumLinesChanged();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to add the movie to the watchlist", e);
        }
    }

    public int removeFromWatchlist(String apiId) throws DatabaseException {
        try {
            List<WatchlistMovieEntity> results = dao.queryBuilder().where().eq("apiId", apiId).query();
            int count = 0;
            for (WatchlistMovieEntity result : results) {
                count += dao.delete(result);
            }
            return count;
        } catch (SQLException e) {
            throw new DatabaseException("Failed to remove the movie from the watchlist", e);
        }
    }
}