package at.ac.fhcampuswien.fhmdb.utils;

import at.ac.fhcampuswien.fhmdb.WatchlistController;
import at.ac.fhcampuswien.fhmdb.database.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.WatchlistController;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class Controller implements Observer {
    private final WatchlistRepository watchlistRepository = new WatchlistRepository();

    public final ClickEventHandler<Movie> onAddToWatchlistClicked = (clickedItem) -> {
        try {
            if (watchlistRepository.getFromWatchlist(clickedItem.getApiID()) != null) {
                update("The movie is already in the watchlist!");
            } else {
                watchlistRepository.addToWatchlist(new WatchlistMovieEntity(clickedItem.getApiID()));
                update("A new movie has been added to the watchlist!");
            }
        } catch (DatabaseException e) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("An error has occurred while adding the movie to the Watchlist: " + e.getMessage());
                alert.showAndWait();
            });
        }
    };

    public final ClickEventHandler<Movie> onRemoveFromWatchlistClicked = (clickedItem) -> {
        try {
            watchlistRepository.removeFromWatchlist(clickedItem.getApiID());
            // Remove the movie from the observableMovies list
            Platform.runLater(() -> {
                WatchlistController.observableMovies.remove(clickedItem);
            });
            update("Movie has been removed from the watchlist");
        } catch (DatabaseException e) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("An error has occurred while removing the movie from the Watchlist: " + e.getMessage());
                alert.showAndWait();
            });
        }
    };

    @Override
    public void update(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Watchlist Information");
            alert.setHeaderText(null);
            alert.setContentText(message);

            alert.showAndWait();
        });
    }
}