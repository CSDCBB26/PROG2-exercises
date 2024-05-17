package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "watchlist_movies")
public class WatchlistMovieEntity {

    @DatabaseField(id = true)
    private long id;

    @DatabaseField(canBeNull = false)
    private String apiId;

    // DO NOT REMOVE - ORMLite requires a no-arg constructor
    public WatchlistMovieEntity() {
    }

    public WatchlistMovieEntity(long id, String apiId) {
        this.id = id;
        this.apiId = apiId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WatchlistMovieEntity that = (WatchlistMovieEntity) o;
        return id == that.id && Objects.equals(apiId, that.apiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, apiId);
    }
}
