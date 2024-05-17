package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.exceptions.MovieAPIException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class WatchlistController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public JFXButton resetBtn;

    @FXML
    public TextField searchField;

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
    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes


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

        homeBtn.setOnAction(actionEvent -> SceneSwitcher.switchScene(actionEvent, "home-view.fxml"));
        watchlistBtn.setOnAction(actionEvent -> SceneSwitcher.switchScene(actionEvent, "watchlist-view.fxml"));

        // set data of observable list to list view
        movieListView.setItems(observableMovies);
        // use custom cell factory to display data
        movieListView.setCellFactory(movieListView -> new MovieCell());
    }


}