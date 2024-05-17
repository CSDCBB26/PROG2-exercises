package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.exceptions.MovieAPIException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import at.ac.fhcampuswien.fhmdb.utils.MovieUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

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

    {
        try {
            allMovies = Movie.initializeMovies();
        } catch (MovieAPIException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allMovies = Movie.initializeMovies();
            observableMovies.addAll(allMovies);

            navToggleButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                if (isSelected) {
                    nav.setVisible(true);
                } else {
                    nav.setVisible(false);
                }
            });

            // set data of observable list to list view
            movieListView.setItems(observableMovies);
            // use custom cell factory to display data
            movieListView.setCellFactory(movieListView -> new MovieCell());


        } catch (MovieAPIException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while initializing the movie list: " + e.getMessage());

            alert.showAndWait();
        }
    }

    public void watchlist(ActionEvent actionEvent) {
    }

    public void home(ActionEvent actionEvent) {
    }

    public void about(ActionEvent actionEvent) {
    }

}