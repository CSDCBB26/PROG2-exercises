package at.ac.fhcampuswien.fhmdb.utils;

import at.ac.fhcampuswien.fhmdb.database.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;

public class Controller {
    private final ClickEventHandler onAddToWatchlistClicked = (clickedItem) -> {
        WatchlistRepository watchlistRepository = new WatchlistRepository();

        try {
            watchlistRepository.addToWatchlist((WatchlistMovieEntity) clickedItem);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    };

}