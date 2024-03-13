package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Movie {
    private String title;
    private String description;
    private List<Genre> genres;

    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "{Title: " + title + "}   \n";
    }

    /**
     * Idea for simple equals method from stackoverflow
     * @param obj
     * @return equals
     */
    @Override
    public boolean equals(Object obj) {
        boolean res = false;
        if(obj instanceof Movie){
            Movie other = (Movie) obj;
            res = this.title.equals(other.title)
                    && this.description.equals(other.description)
                    && this.genres.equals(other.genres);
        }
        return res;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
    public List<Genre> getGenres(){
        return genres;
    };

    public static List<Movie> initializeMovies(){
        return List.of(
                new Movie("Superman", "Description of Superman", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)),
                new Movie("Batman Begins", "Batman the beginning", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.SCIENCE_FICTION)),
                new Movie("Peaky Blinders", "Peaky Blinders description", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA)),
                new Movie("Ironman", "Ironman 1", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)),
                new Movie("Thor: Love and Thunder", "Thor 3 ", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY, Genre.SCIENCE_FICTION)),
                new Movie("The Dark Knight", "Batman the Dark Knight", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("The Dark Knight Rises", "Batman the Dark Knight Rises", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("The Dark Knight Returns", "Batman the Dark Knight Returns", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.DRAMA, Genre.THRILLER, Genre.ANIMATION)),
                new Movie("Spiderman: No Way Home", "Spiderman 3", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)),
                new Movie("The Avengers", "The Avengers", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)),
                new Movie("Documentation about the Universe", "Documentation about the Universe", List.of(Genre.DOCUMENTARY))
        );
    }

}