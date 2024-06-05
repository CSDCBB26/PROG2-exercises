package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    private final Dao<MovieEntity, Long> dao;

    private static MovieRepository movieRepository;

    private MovieRepository(Dao<MovieEntity, Long> dao) {
        this.dao = dao;
    }

    public List<MovieEntity> getAllMovies() {
        List<MovieEntity> movieEntities = new ArrayList<>();

        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            System.out.println("Failed to retrieve all movies " + e.getMessage());
        }

        return movieEntities;
    }

    public int removeAll() throws DatabaseException {
        try {
            return dao.deleteBuilder().delete();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to remove all movies", e);
        }
    }

    public MovieEntity getMovie(String apiId) throws DatabaseException {
        try {
            List<MovieEntity> result = dao.queryForEq("apiId", apiId);
            if (result != null && !result.isEmpty()) {
                return result.get(0);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve movie with apiId: " + apiId, e);
        }
    }


    public int addAllMovies(List<MovieEntity> movies) throws DatabaseException {
        int count = 0;
        for (MovieEntity movie : movies) {
            try {
                count += dao.createOrUpdate(movie).getNumLinesChanged();
            } catch (SQLException e) {
                throw new DatabaseException("Failed to add all movies", e);
            }
        }
        return count;
    }

    public static MovieRepository getMovieRepository() {
        initializeMovieRepository(null);
        return movieRepository;
    }

    public static MovieRepository getMovieRepository(Dao<MovieEntity, Long> dao) {
        initializeMovieRepository(dao);
        return movieRepository;
    }

    protected static void initializeMovieRepository(Dao<MovieEntity, Long> dao) {
        if (movieRepository != null) {
            return;
        }

        if (dao == null) {
            dao = getDefaultMovieDao();
        }

        movieRepository = new MovieRepository(dao);
    }

    protected static Dao<MovieEntity, Long> getDefaultMovieDao() {
        return DatabaseManager.getDatabaseInstance().getMovieDao();
    }
}