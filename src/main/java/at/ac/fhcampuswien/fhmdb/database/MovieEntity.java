package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;
import java.util.stream.Collectors;

@DatabaseTable(tableName = "movies")
public class MovieEntity {

    @DatabaseField(id = true)
    private String apiId;

    @DatabaseField(canBeNull = false)
    private String title;

    @DatabaseField
    private String description;

    @DatabaseField
    private String genres;

    @DatabaseField
    private String directors;

    @DatabaseField
    private int releaseYear;

    @DatabaseField
    private String imgUrl;

    @DatabaseField
    private int lengthInMinutes;

    @DatabaseField
    private double rating;

    // DO NOT REMOVE - ORMLite requires a no-arg constructor
    public MovieEntity() {
    }

    private MovieEntity(Builder builder) {
        this.apiId = builder.apiId;
        this.title = builder.title;
        this.description = builder.description;
        this.genres = listToString(builder.genres);
        this.directors = listToString(builder.directors);
        this.releaseYear = builder.releaseYear;
        this.imgUrl = builder.imgUrl;
        this.lengthInMinutes = builder.lengthInMinutes;
        this.rating = builder.rating;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public static <T> String listToString(List<T> list) {
        return list.stream().map(Object::toString).collect(Collectors.joining(","));
    }

    public static String genresToString(List<Genre> genres) {
        return listToString(genres);
    }

    public static String directorsToString(List<String> directors) {
        return listToString(directors);
    }

    public static List<MovieEntity> fromMovies(List<Movie> movies) {
        return movies.stream()
                .map(movie -> new MovieEntity.Builder()
                        .setApiID(movie.getId())
                        .setTitle(movie.getTitle())
                        .setDescription(movie.getDescription())
                        .setGenres(movie.getGenres())
                        .setDirectors(movie.getDirectors())
                        .setReleaseYear(movie.getReleaseYear())
                        .setImgUrl(movie.getImgUrl())
                        .setLengthInMinutes(movie.getLengthInMinutes())
                        .setRating(movie.getRating())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public static List<Movie> toMovies(List<MovieEntity> movieEntities) {
        return movieEntities.stream()
                .map(entity -> new Movie.Builder()
                        .setId(entity.getApiId())
                        .setAppID(entity.getApiId())
                        .setTitle(entity.getTitle())
                        .setDescription(entity.getDescription())
                        .setGenres(Genre.stringToGenres(entity.getGenres()))
                        .setDirectors(List.of(entity.getDirectors().split(",")))
                        .setReleaseYear(entity.getReleaseYear())
                        .setimgUrl(entity.getImgUrl())
                        .setLengthInMinutes(entity.getLengthInMinutes())
                        .setRating(entity.getRating())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public static class Builder {
        private String apiId;
        private String title;
        private String description;
        private List<Genre> genres;
        private int releaseYear;
        private String imgUrl;
        private int lengthInMinutes;
        private List<String> directors;
        private double rating;

        public Builder setApiID(String apiId) {
            this.apiId = apiId;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setGenres(List<Genre> genres) {
            this.genres = genres;
            return this;
        }

        public Builder setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
            return this;
        }

        public Builder setReleaseYear(int releaseYear) {
            this.releaseYear = releaseYear;
            return this;
        }

        public Builder setLengthInMinutes(int lengthInMinutes) {
            this.lengthInMinutes = lengthInMinutes;
            return this;
        }

        public Builder setDirectors(List<String> directors) {
            this.directors = directors;
            return this;
        }

        public Builder setRating(double rating) {
            this.rating = rating;
            return this;
        }

        public MovieEntity build() {
            return new MovieEntity(this);
        }
    }
}