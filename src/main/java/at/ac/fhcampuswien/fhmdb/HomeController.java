package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.exceptions.MovieAPIException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.sortState.AscendingSortState;
import at.ac.fhcampuswien.fhmdb.sortState.DescendingSortState;
import at.ac.fhcampuswien.fhmdb.sortState.MovieSorter;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import at.ac.fhcampuswien.fhmdb.utils.Controller;
import at.ac.fhcampuswien.fhmdb.utils.MovieUtils;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.*;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public JFXButton resetBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView<Movie> movieListView;

    @FXML
    public JFXComboBox<Genre> genreComboBox;
    @FXML
    public JFXComboBox<Integer> releaseYearComboBox;
    @FXML
    public JFXSlider ratingSlider;
    @FXML
    public TextField ratingField;
    @FXML
    public JFXButton sortBtn;

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

    ControllerFactory controllerFactory = new ControllerFactory();
    private Controller controller = (Controller) controllerFactory.call(Controller.class);

    private MovieSorter movieSorter;

    public List<Movie> allMovies;
    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    {
        try {
            allMovies = Movie.initializeMovies();
            movieSorter = new MovieSorter(allMovies); // initial state unsorted
        } catch (MovieAPIException e) {
            throw new RuntimeException(e);
        }
    }

    protected void handleFilterAction() {
        Genre genre = genreComboBox.getValue();
        int releaseYear = releaseYearComboBox.getValue() == null ? 0 : releaseYearComboBox.getValue();
        double ratingFrom = ratingSlider.getValue();

        System.out.println("genre: " + genreComboBox.getValue() + "\nrelease: " + releaseYear + "\nrating: " + ratingFrom);

        List<Movie> temp = MovieUtils.filter(genre, searchField.getText(), releaseYear, ratingFrom);
        observableMovies.clear();
        observableMovies.addAll(temp);

        // Update movieSorter with filtered movies
        // and also Apply current sort state to filtered movies
        movieSorter = new MovieSorter(temp);
        movieSorter.sort();

        movieListView.setItems(observableMovies);
    }

    protected void onEnterKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            System.out.print("Enter pressed, searching for:  ");
            System.out.println(searchField.getText());

            handleFilterAction();
        }
    }

    protected void onSortButtonClick() {
        if (sortBtn.getText().equals("Sort (asc)")) {
            movieSorter.setState(new AscendingSortState());
            sortBtn.setText("Sort (desc)");
        } else {
            movieSorter.setState(new DescendingSortState());
            sortBtn.setText("Sort (asc)");
        }

        movieSorter.sort();
        observableMovies.setAll(movieSorter.getMovies());
    }

    protected void onResetButtonClick() {
        searchField.clear();
        genreComboBox.setValue(null);
        releaseYearComboBox.setValue(null);
        ratingSlider.setValue(0);
        ratingField.clear();
        observableMovies.clear();
        observableMovies.addAll(allMovies);
        movieSorter = new MovieSorter(allMovies); // Reset movieSorter
        sortBtn.setText("Sort (asc)");
    }

    protected void ratingFieldEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            System.out.print("Enter pressed, setting value to:  ");
            System.out.println(ratingField.getText());
            if (ratingField.getText().matches("[0-9]*[.0-9]+")) {
                ratingSlider.setValue(Double.parseDouble(ratingField.getText()));
                System.out.println("set!!!");
            } else ratingField.clear();

            handleFilterAction();
        }
    }

    private static List<Integer> getAllReleaseYears(List<Movie> allMovies) {
        List<Integer> temp = new ArrayList<>();

        for (Movie movie : allMovies) {
            if (!temp.contains(movie.getReleaseYear())) temp.add(movie.getReleaseYear());
        }

        temp.sort(Integer::compare);

        return temp;
    }

    private static List<Genre> getAllGenres(List<Movie> allMovies) {
        List<Genre> temp = new ArrayList<>();

        for (Movie movie : allMovies) {
            for (Genre genre : movie.getGenres()) {
                if (!temp.contains(genre)) temp.add(genre);
            }
        }

        temp.sort(Comparator.comparing(Genre::name));

        return temp;
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
            movieListView.setCellFactory(movieListView -> new MovieCell(controller.onAddToWatchlistClicked, false));
            genreComboBox.getItems().addAll(getAllGenres(allMovies));
            genreComboBox.setPromptText("Filter by Genre");


            releaseYearComboBox.setPromptText("Filter by Release Year");
            releaseYearComboBox.getItems().addAll(getAllReleaseYears(allMovies));

            ratingSlider.setValue(0);
            ratingSlider.setMax(10);
            ratingSlider.setBlockIncrement(1);
            ratingSlider.setShowTickLabels(true);
            ratingSlider.setShowTickMarks(true);
            ratingSlider.setMajorTickUnit(5);

            Tooltip t = new Tooltip("Rating");
            Tooltip.install(ratingSlider, t);


            // items will be filtered directly after selecting a value from combobox
            genreComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> handleFilterAction());
            releaseYearComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> handleFilterAction());

            ratingField.setOnKeyPressed(this::ratingFieldEnter);

            ratingSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                double temp = newValue.doubleValue();
                temp = Math.round(temp * 10) / 10.;
                ratingField.setText(String.valueOf(temp));
            });

            searchBtn.setOnAction(actionEvent -> handleFilterAction());
            searchField.setOnKeyPressed(this::onEnterKeyPressed);
            sortBtn.setOnAction(actionEvent -> onSortButtonClick());
            resetBtn.setOnAction(actionEvent -> onResetButtonClick());
            homeBtn.setOnAction(actionEvent -> Scene.switchScene(actionEvent, "home-view.fxml"));
            watchlistBtn.setOnAction(actionEvent -> Scene.switchScene(actionEvent, "watchlist-view.fxml"));

        } catch (MovieAPIException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while initializing the movie list: " + e.getMessage());

            alert.showAndWait();
        }
    }

}