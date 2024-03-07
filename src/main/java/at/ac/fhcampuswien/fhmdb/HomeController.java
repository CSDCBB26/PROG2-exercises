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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    public static List<Movie> search(String input, List<Movie> movieList) {
        //TODO implement method
        return new ArrayList<>();
    }

    public static List<Movie> filter(Genre input, List<Movie> movieList) {
        //TODO implement method
        if(input == Genre.ALL_GENRES)
            return movieList;

        List<Movie> result = movieList.stream().filter(movie -> movie.getGenres().contains(input)).toList();

        return result;
    }

    public static List<Movie> sort(String mode, List<Movie> input){
        if(input.isEmpty()){
            return input;
        }
        return new ArrayList<>();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genre.class.getEnumConstants());


        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here
        searchBtn.setOnAction(actionEvent -> {

            System.out.print("Filter set to genre:   ");
            System.out.println(genreComboBox.getValue());

            // TODO call filter method here:
            movieListView.setCellFactory(movieListView -> new MovieCell());
            List<Movie> temp = filter( (Genre) genreComboBox.getValue(), allMovies);
            movieListView.setCellFactory(movieListView -> new MovieCell());
            observableMovies.clear();
            movieListView.setCellFactory(movieListView -> new MovieCell());
            observableMovies.addAll(temp);
            movieListView.setItems(observableMovies);
            movieListView.setCellFactory(movieListView -> new MovieCell());

        });

        searchField.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)) {

                System.out.print("Enter pressed, searching for:  ");
                System.out.println(searchField.getText());

                // TODO call search method here:
            }
        });

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending
                sortBtn.setText("Sort (desc)");
            } else {
                // TODO sort observableMovies descending
                sortBtn.setText("Sort (asc)");
            }
        });


    }
}