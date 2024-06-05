package at.ac.fhcampuswien.fhmdb.sortState;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DescendingSortState implements SortState {
    @Override
    public void sort(List<Movie> movies) {
        Collections.sort(movies, Comparator.comparing(Movie::getTitle).reversed());
    }
}
