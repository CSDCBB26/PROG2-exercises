package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:h2:./database/fhmdb";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "pass";
    private ConnectionSource connectionSource;
    private Dao<MovieEntity, Long> movieDao;
    private Dao<WatchlistMovieEntity, Long> watchlistMovieDao;

    private static DatabaseManager instance;

    /**
     * Singleton pattern - provide only one private constructor
     */
    private DatabaseManager() {
        try {

            createConnectionSource();
            movieDao = DaoManager.createDao(connectionSource, MovieEntity.class);
            watchlistMovieDao = DaoManager.createDao(connectionSource, WatchlistMovieEntity.class);
            initializeDatabase();

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        }
    }

    /**
     * Singleton pattern - provide method for getting the DB instance
     */
    public static DatabaseManager getDatabaseInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private void initializeDatabase() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, MovieEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, WatchlistMovieEntity.class);
    }

    private void createConnectionSource() throws SQLException {
        connectionSource = new JdbcConnectionSource(DATABASE_URL, USERNAME, PASSWORD);
    }

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    public Dao<MovieEntity, Long> getMovieDao() {
        return movieDao;
    }

    public Dao<WatchlistMovieEntity, Long> getWatchlistMovieDao() {
        return watchlistMovieDao;
    }
}