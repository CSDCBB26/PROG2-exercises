package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Movie {
    private String title;
    private String description;
    private List<Genre> genres;
    public static final Set<String> ALL_GENRES = Set.of("ACTION", "ADVENTURE", "ANIMATION", "BIOGRAPHY",
            "COMEDY", "CRIME", "DRAMA", "DOCUMENTARY", "FAMILY", "FANTASY", "HISTORY", "HORROR", "MUSICAL",
            "MYSTERY", "ROMANCE", "SCIENCE_FICTION", "SPORT", "THRILLER", "WAR", "WESTERN");

    // TODO add more properties here

    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        // TODO add some dummy data here
        movies.add(new Movie("Test", "Test", List.of(Genre.ACTION)));

        return movies;
    }

}