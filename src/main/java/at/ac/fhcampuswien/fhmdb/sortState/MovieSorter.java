package at.ac.fhcampuswien.fhmdb.sortState;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.List;

public class MovieSorter {
    private SortState state;
    private List<Movie> movies;

    public MovieSorter(List<Movie> movies) {
        this.movies = movies;
        // initial state is unsorted
        this.state = new UnsortedState();
    }

    public void setState(SortState state) {
        this.state = state;
    }

    public void sort() {
        state.sort(movies);
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
