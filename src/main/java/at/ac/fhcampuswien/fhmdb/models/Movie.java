package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.Genre;
import at.ac.fhcampuswien.fhmdb.database.MovieEntity;
import at.ac.fhcampuswien.fhmdb.database.MovieRepository;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.exceptions.MovieAPIException;
import at.ac.fhcampuswien.fhmdb.utils.MovieAPI;
import at.ac.fhcampuswien.fhmdb.utils.MovieUtils;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String id; // same as appID but there is not appID in the movie provided by the API, there is only id, so
                       // we need to define id as instance variable in order to correctly parse the movies
    private String appID;
    private String title;
    private String description;
    private List<Genre> genres;
    private int releaseYear;
    private String imgUrl;
    private int lengthInMinutes;
    private List<String> directors;
    private List<String> writers;
    private List<String> mainCast;
    private double rating;

    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.genres = genres;
        this.description = description;
    }

    public Movie(String appID, String title, String description, List<Genre> genres) {
        this.appID = appID;
        this.title = title;
        this.genres = genres;
        this.description = description;
    }

    public Movie(String appID, String title, String description, List<Genre> genres, int releaseYear, String imgUrl, int lengthInMinutes, List<String> directors, List<String> writers, List<String> mainCast, int rating) {
        this.appID = appID;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.directors = directors;
        this.writers = writers;
        this.mainCast = mainCast;
        this.rating = rating;
    }

    private Movie(Builder builder) {
        this.appID = builder.appID;
        this.title = builder.title;
        this.description = builder.description;
        this.genres = builder.genres;
        this.releaseYear = builder.releaseYear;
        this.imgUrl = builder.imgUrl;
        this.lengthInMinutes = builder.lengthInMinutes;
        this.directors = builder.directors;
        this.writers = builder.writers;
        this.mainCast = builder.mainCast;
        this.rating = builder.rating;
    }

    public static class Builder {
        private String id;
        private String appID;
        private String title;
        private String description;
        private List<Genre> genres;
        private int releaseYear;
        private String imgUrl;
        private int lengthInMinutes;
        private List<String> directors;
        private List<String> writers;
        private List<String> mainCast;
        private double rating;

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

        public Builder setimgUrl(String imgUrl) {
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

        public Builder setWriters(List<String> writers) {
            this.writers = writers;
            return this;
        }

        public Builder setMainCast(List<String> mainCast) {
            this.mainCast = mainCast;
            return this;
        }

        public Builder setRating(double rating) {
            this.rating = rating;
            return this;
        }

        public Builder setAppID(String appID) {
            this.appID = appID;
            return this;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }

    @Override
    public String toString() {
        return "{Title: " + title + "}   \n";
    }

    /**
     * Idea for simple equals method from stackoverflow
     *
     * @param obj
     * @return equals
     */
    @Override
    public boolean equals(Object obj) {
        boolean res = false;
        if (obj instanceof Movie) {
            Movie other = (Movie) obj;
            res = this.title.equals(other.title)
                    && this.description.equals(other.description)
                    && this.genres.equals(other.genres);
        }
        return res;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    ;

    public String getImgUrl() {
        return imgUrl;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public List<String> getMainCast() {
        return mainCast;
    }

    public double getRating() {
        return rating;
    }

    public String getAppID() {
        return appID;
    }

    public static List<Movie> initializeMovies() throws MovieAPIException {
        MovieRepository movieRepository = new MovieRepository();
        List<Movie> movieList = new ArrayList<>();

        try {
            movieList = MovieEntity.toMovies(movieRepository.getAllMovies());

            // use the API only if no movies set in the database
            if (movieList.isEmpty()) {
                movieList = MovieUtils.parseMovies(MovieAPI.getAllMovies(MovieAPI.API_URL));
                movieRepository.addAllMovies(MovieEntity.fromMovies(movieList));
            }

        } catch (DatabaseException e) {
            System.out.println("Error while adding movies to database " + e.getMessage());
        }

        return movieList;
    }

}