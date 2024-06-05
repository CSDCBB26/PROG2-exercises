package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.utils.Observable;
import at.ac.fhcampuswien.fhmdb.utils.Observer;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class WatchlistRepository implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private Dao<WatchlistMovieEntity, Long> dao;

    private static WatchlistRepository watchlistRepository;

    private WatchlistRepository(Dao<WatchlistMovieEntity, Long> dao) {
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
            Dao.CreateOrUpdateStatus status = dao.createOrUpdate(movie);
            int numLinesChanged = status.getNumLinesChanged();

            if (status.isCreated()) {
                notifyObservers("The movie has been added to the watchlist.");
            }

            return numLinesChanged;
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

            notifyObservers("The movie has been removed from the watchlist.");

            return count;
        } catch (SQLException e) {
            throw new DatabaseException("Failed to remove the movie from the watchlist", e);
        }
    }

    @Override
    public void addObserver(Object observer) {
        observers.add((Observer) observer);
    }

    @Override
    public void removeObserver(Object observer) {
        observers.remove((Observer) observer);
    }

    @Override
    public void notifyObservers(Object message) {
        for (Observer observer : observers) {
            observer.update((String) message);
        }
    }

    public static WatchlistRepository getWatchlistRepository() {
        initializeWatchlistRepository(null);
        return watchlistRepository;
    }

    public static WatchlistRepository getWatchlistRepository(Dao<WatchlistMovieEntity, Long> dao) {
        initializeWatchlistRepository(dao);
        return watchlistRepository;
    }

    protected static void initializeWatchlistRepository(Dao<WatchlistMovieEntity, Long> dao) {
        if (watchlistRepository != null) {
            return;
        }

        if (dao == null) {
            dao = getDefaultWatchlistDao();
        }

        watchlistRepository = new WatchlistRepository(dao);
    }

    protected static Dao<WatchlistMovieEntity, Long> getDefaultWatchlistDao() {
        return DatabaseManager.getDatabaseInstance().getWatchlistMovieDao();
    }

}