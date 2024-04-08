package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.utils.MovieUtils;
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
    public JFXComboBox<Integer> ratingComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes



    protected void handleFilterAction_old() {
        System.out.print("Filter set to genre:   ");
        System.out.println(genreComboBox.getValue());

        List<Movie> temp = MovieUtils.filter(genreComboBox.getValue(), allMovies, searchField.getText());
        observableMovies.clear();
        observableMovies.addAll(temp);
        movieListView.setItems(observableMovies);
    }

    protected void handleFilterAction_re() {
        Genre genre = genreComboBox.getValue();
        int releaseYear = releaseYearComboBox.getValue() == null ? 0 : releaseYearComboBox.getValue();
        int ratingFrom = ratingComboBox.getValue() == null ? 0 : ratingComboBox.getValue();

        System.out.println("genre: " + genreComboBox.getValue() + "\nrelease: " + releaseYear + "\nrating: " + ratingFrom);

        List<Movie> temp = MovieUtils.filter_re(genre, searchField.getText(), releaseYear, ratingFrom);
        observableMovies.clear();
        observableMovies.addAll(temp);
        movieListView.setItems(observableMovies);
    }

    protected void onEnterKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)) {
            System.out.print("Enter pressed, searching for:  ");
            System.out.println(searchField.getText());

            handleFilterAction_re();
        }
    }

    protected void onSortButtonClick() {
        if(sortBtn.getText().equals("Sort (asc)")) {
            MovieUtils.sort("ascending", observableMovies);
            sortBtn.setText("Sort (desc)");
        } else {
            MovieUtils.sort("descending", observableMovies);
            sortBtn.setText("Sort (asc)");
        }
    }

    protected void onResetButtonClick(){
        searchField.clear();
        //genreComboBox.setValue(Genre.ALL);
        genreComboBox.setValue(null);
        releaseYearComboBox.setValue(null);
        ratingComboBox.setValue(null);
        observableMovies.clear();
        observableMovies.addAll(allMovies);
    }

    private static List<Integer> getAllReleaseYears(List<Movie> allMovies){
        List<Integer> temp = new ArrayList<>();

        for (Movie movie : allMovies) {
            if(!temp.contains(movie.getReleaseYear()))
                temp.add(movie.getReleaseYear());
        }

        temp.sort(Integer::compare);

        return temp;
    }

    private static List<Genre> getAllGenres(List<Movie> allMovies){
        List<Genre> temp = new ArrayList<>();

        for (Movie movie : allMovies) {
            for(Genre genre : movie.getGenres()){
                if(!temp.contains(genre))
                    temp.add(genre);
            }
        }

        temp.sort(Comparator.comparing(Genre::name));

        return temp;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);

        // set data of observable list to list view
        movieListView.setItems(observableMovies);
        // use custom cell factory to display data
        movieListView.setCellFactory(movieListView -> new MovieCell());

        //genreComboBox.getItems().addAll(Genre.class.getEnumConstants());
        //genreComboBox.setValue(Genre.ALL);
        genreComboBox.getItems().addAll(getAllGenres(allMovies));
        genreComboBox.setPromptText("Filter by Genre");

        //releaseYearComboBox.getItems().add
        releaseYearComboBox.setPromptText("Filter by Release Year");
        releaseYearComboBox.getItems().addAll(getAllReleaseYears(allMovies));



        for(int i = 1; i <= 10; ++i){
            ratingComboBox.getItems().add(i);
        }
        ratingComboBox.setPromptText("Filter by Rating");

        // items will be filtered directly after selecting a value from combobox
        genreComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> handleFilterAction_re());
        releaseYearComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> handleFilterAction_re());
        ratingComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> handleFilterAction_re());

        searchBtn.setOnAction(actionEvent -> handleFilterAction_re());
        searchField.setOnKeyPressed(this::onEnterKeyPressed);
        sortBtn.setOnAction(actionEvent -> onSortButtonClick());
        resetBtn.setOnAction(actionEvent -> onResetButtonClick());

    }
}