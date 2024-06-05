package at.ac.fhcampuswien.fhmdb.sortState;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import java.util.List;

public class UnsortedState implements SortState {
    @Override
    public void sort(List<Movie> movies) {
        // Do nothing, movies are unsorted
    }
}