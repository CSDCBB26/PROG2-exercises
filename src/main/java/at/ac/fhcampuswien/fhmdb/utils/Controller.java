package at.ac.fhcampuswien.fhmdb.utils;

import at.ac.fhcampuswien.fhmdb.database.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Movie;

public class Controller {
    private WatchlistRepository watchlistRepository = new WatchlistRepository();

    public final ClickEventHandler<Movie> onAddToWatchlistClicked = (clickedItem) -> {
        try {
            watchlistRepository.addToWatchlist(new WatchlistMovieEntity(clickedItem.getId(), clickedItem.getAppID()));
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    };

    private final ClickEventHandler<WatchlistMovieEntity> onRemoveFromWatchlistClicked = (clickedItem) -> {
        try {
            watchlistRepository.removeFromWatchlist(String.valueOf(clickedItem));
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    };

}