package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MovieRepository {

    private Dao<MovieEntity, Long> dao;

    public MovieRepository(Dao<MovieEntity, Long> dao) {
        this.dao = dao;
    }

    public List<MovieEntity> getAllMovies() throws SQLException {
        return dao.queryForAll();
    }

    public int removeAll() throws SQLException {
        return dao.deleteBuilder().delete();
    }

    public MovieEntity getMovie(Long id) throws SQLException {
        return dao.queryForId(id);
    }

    public int addAllMovies(List<MovieEntity> movies) throws SQLException {
        int count = 0;
        for (MovieEntity movie : movies) {
            count += dao.createOrUpdate(movie).getNumLinesChanged();
        }
        return count;
    }
}