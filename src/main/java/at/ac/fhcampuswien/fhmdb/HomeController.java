package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.utils.MovieUtils;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.NumberStringConverter;

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

    public List<Movie> allMovies = Movie.initializeMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes


    protected void handleFilterAction() {
        Genre genre = genreComboBox.getValue();
        int releaseYear = releaseYearComboBox.getValue() == null ? 0 : releaseYearComboBox.getValue();
        double ratingFrom = ratingSlider.getValue();

        System.out.println("genre: " + genreComboBox.getValue() + "\nrelease: " + releaseYear + "\nrating: " + ratingFrom);

        List<Movie> temp = MovieUtils.filter(genre, searchField.getText(), releaseYear, ratingFrom);
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
            MovieUtils.sort("ascending", observableMovies);
            sortBtn.setText("Sort (desc)");
        } else {
            MovieUtils.sort("descending", observableMovies);
            sortBtn.setText("Sort (asc)");
        }
    }

    protected void onResetButtonClick(){
        searchField.clear();
        genreComboBox.setValue(null);
        releaseYearComboBox.setValue(null);
        ratingSlider.setValue(0);
        ratingField.clear();
        observableMovies.clear();
        observableMovies.addAll(allMovies);
    }

    protected void ratingFieldEnter(KeyEvent keyEvent){
        if(keyEvent.getCode().equals(KeyCode.ENTER)) {
            System.out.print("Enter pressed, setting value to:  ");
            System.out.println(ratingField.getText());
        if(ratingField.getText().matches("[0-9]*[.0-9]+")) {
            ratingSlider.setValue(Double.parseDouble(ratingField.getText()));
            System.out.println("set!!!");
        }
        else ratingField.clear();
        }
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
        Tooltip.install(ratingSlider,t);


        // items will be filtered directly after selecting a value from combobox
        genreComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> handleFilterAction());
        releaseYearComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> handleFilterAction());

        ratingField.setOnKeyPressed(this::ratingFieldEnter);

        ratingSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double temp = newValue.doubleValue();
            temp = Math.round(temp*10)/10.;
            ratingField.setText(String.valueOf(temp));
        });

        //ratingField.setText("0");

        searchBtn.setOnAction(actionEvent -> handleFilterAction());
        searchField.setOnKeyPressed(this::onEnterKeyPressed);
        sortBtn.setOnAction(actionEvent -> onSortButtonClick());
        resetBtn.setOnAction(actionEvent -> onResetButtonClick());

    }
}