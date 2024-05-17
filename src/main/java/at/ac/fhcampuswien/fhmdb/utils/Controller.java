package at.ac.fhcampuswien.fhmdb.utils;

import at.ac.fhcampuswien.fhmdb.database.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Movie;

public class Controller {
    private final WatchlistRepository watchlistRepository = new WatchlistRepository();

    public final ClickEventHandler<Movie> onAddToWatchlistClicked = (clickedItem) -> {
        try {
            if (watchlistRepository.getFromWatchlist(clickedItem.getDatabaseId()) != null) {
                System.out.println("Movie already in the watchlist");
                return;
            }

            watchlistRepository.addToWatchlist(new WatchlistMovieEntity(clickedItem.getDatabaseId(), clickedItem.getAppID()));
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    };

    public final ClickEventHandler<Movie> onRemoveFromWatchlistClicked = (clickedItem) -> {
        try {
            watchlistRepository.removeFromWatchlist(clickedItem.getAppID());
            // WatchlistController.updateWatchListMovies();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    };

}