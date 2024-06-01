package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import at.ac.fhcampuswien.fhmdb.utils.Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WatchlistController implements Initializable {
    private Controller controller = new Controller();

    @FXML
    public JFXListView<Movie> movieListView;

    @FXML
    private VBox nav;

    @FXML
    private ToggleButton navToggleButton;

    @FXML
    public JFXButton watchlistBtn;

    @FXML
    public JFXButton homeBtn;

    @FXML
    public JFXButton aboutBtn;

    public List<Movie> allMovies;
    public static ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    public void updateWatchListMovies() {
        observableMovies.clear();
        allMovies = Movie.initializeWatchListMovies();
        observableMovies.addAll(allMovies);
        movieListView.setItems(observableMovies);
        movieListView.setCellFactory(movieListView -> new MovieCell(controller.onRemoveFromWatchlistClicked, true));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allMovies = Movie.initializeWatchListMovies();
        observableMovies.addAll(allMovies);


        navToggleButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                nav.setVisible(true);
            } else {
                nav.setVisible(false);
            }
        });


        // Add a listener to the observableMovies list
        observableMovies.addListener((ListChangeListener.Change<? extends Movie> change) -> {
            while (change.next()) {
                if (change.wasRemoved()) {
                    // Update the movieListView with the new list of movies
                    movieListView.setItems(observableMovies);
                }
            }
        });


        homeBtn.setOnAction(actionEvent -> Scene.switchScene(actionEvent, "home-view.fxml"));
        watchlistBtn.setOnAction(actionEvent -> Scene.switchScene(actionEvent, "watchlist-view.fxml"));

        // set data of observable list to list view
        movieListView.setItems(observableMovies);
        // use custom cell factory to display data
        movieListView.setCellFactory(movieListView -> new MovieCell(controller.onRemoveFromWatchlistClicked, true));
    }


}