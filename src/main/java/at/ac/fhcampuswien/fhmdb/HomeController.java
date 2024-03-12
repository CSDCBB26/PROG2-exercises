package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    public static List<Movie> search(String input, List<Movie> movieList) {
        return movieList.stream()
                .filter(movie -> isMovieMatchesSearchQuery(movie, input))
                .collect(Collectors.toList());
    }

    public static List<Movie> filter(Genre selectedGenre, List<Movie> movieList, String searchQuery) {
        List<Movie> filteredList = selectedGenre == null || selectedGenre == Genre.ALL
                ? movieList.stream().toList()
                : movieList.stream().filter(movie -> movie.getGenres().contains(selectedGenre)).toList();

        if (!searchQuery.isBlank()) {
            filteredList = search(searchQuery, filteredList);
        }

        return filteredList;
    }

    protected static boolean isMovieMatchesSearchQuery(Movie movie, String searchQuery) {
        return movie.getTitle().toLowerCase().contains(searchQuery.toLowerCase()) ||
                movie.getDescription().toLowerCase().contains(searchQuery.toLowerCase());
    }

    public static List<Movie> sort(String mode, List<Movie> movieList){
        if(movieList.isEmpty()){
            return movieList;
        }

        if ("descending".equals(mode)) {
            movieList.sort(Comparator.comparing(Movie::getTitle).reversed());
        } else {
            movieList.sort(Comparator.comparing(Movie::getTitle));
        }

        return movieList;
    }

    protected void handleFilterAction() {
        System.out.print("Filter set to genre:   ");
        System.out.println(genreComboBox.getValue());

        List<Movie> temp = filter(genreComboBox.getValue(), allMovies, searchField.getText());
        observableMovies.clear();
        observableMovies.addAll(temp);
        movieListView.setItems(observableMovies);
    }

    protected void onEnterKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)) {
            System.out.print("Enter pressed, searching for:  ");
            System.out.println(searchField.getText());

            handleFilterAction();
        }
    }

    protected void onSortButtonClick() {
        if(sortBtn.getText().equals("Sort (asc)")) {
            sort("ascending", observableMovies);
            sortBtn.setText("Sort (desc)");
        } else {
            sort("descending", observableMovies);
            sortBtn.setText("Sort (asc)");
        }
    }

    protected void onResetButtonClick(){
        searchField.clear();
        genreComboBox.setValue(Genre.ALL);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);

        // set data of observable list to list view
        movieListView.setItems(observableMovies);
        // use custom cell factory to display data
        movieListView.setCellFactory(movieListView -> new MovieCell());

        genreComboBox.getItems().addAll(Genre.class.getEnumConstants());
        genreComboBox.setValue(Genre.ALL);
        // items will be filtered directly after selecting a value from combobox
        genreComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> handleFilterAction());

        searchBtn.setOnAction(actionEvent -> handleFilterAction());
        searchField.setOnKeyPressed(this::onEnterKeyPressed);
        sortBtn.setOnAction(actionEvent -> onSortButtonClick());
        resetBtn.setOnAction(actionEvent -> onResetButtonClick());

    }
}