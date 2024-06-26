package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MovieRepository {

    private final Dao<MovieEntity, Long> dao;

    public MovieRepository() {
        this.dao = DatabaseManager.getDatabaseInstance().getMovieDao();
    }

    public MovieRepository(Dao<MovieEntity, Long> dao) {
        this.dao = dao;
    }

    public List<MovieEntity> getAllMovies() throws DatabaseException {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve all movies", e);
        }
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
}