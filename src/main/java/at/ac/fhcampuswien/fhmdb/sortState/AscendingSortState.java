package at.ac.fhcampuswien.fhmdb.sortState;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.Comparator;
import java.util.List;

public class AscendingSortState implements SortState {
    @Override
    public void sort(List<Movie> movies) {
        movies.sort(Comparator.comparing(Movie::getTitle));
    }
}
