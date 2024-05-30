package at.ac.fhcampuswien.fhmdb.utils;

import at.ac.fhcampuswien.fhmdb.WatchlistController;
import at.ac.fhcampuswien.fhmdb.database.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.WatchlistController;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class Controller {
    private final WatchlistRepository watchlistRepository = new WatchlistRepository();

    public final ClickEventHandler<Movie> onAddToWatchlistClicked = (clickedItem) -> {
        try {
            if (watchlistRepository.getFromWatchlist(clickedItem.getApiID()) != null) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Watchlist Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Movie already in the watchlist");

                    alert.showAndWait();
                });
                return;
            }

            watchlistRepository.addToWatchlist(new WatchlistMovieEntity(clickedItem.getApiID()));
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    };

    public final ClickEventHandler<Movie> onRemoveFromWatchlistClicked = (clickedItem) -> {
        try {
            watchlistRepository.removeFromWatchlist(clickedItem.getApiID());
            // Remove the movie from the observableMovies list
            Platform.runLater(() -> {
                WatchlistController.observableMovies.remove(clickedItem);
            });
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    };
}