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
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

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

    //ToDO by Andreas Drozd implement method
    public static List<Movie> search(String input, List<Movie> movieList) {
        List<Movie> searchList =  new ArrayList<>();
        for(Movie movie : movieList){
            if(movie.getDescription().toLowerCase().contains(input.toLowerCase()) || movie.getTitle().contains(input.toLowerCase())){
                searchList.add(movie);
            }
        }
        return searchList;
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

    //ToDo by Jakob

    /**
     * Bubblesort algorithm
     * @param mode Whether to sort in ascending or descending order
     * @param input List of Movies to sort
     * @return the sorted List
     */
    public static List<Movie> sort(String mode, List<Movie> input){
        if(input.isEmpty()){
            return input;
        }
        Movie temp;

        for(int i = 0; i < input.size()-1; ++i){

            for(int j = 0; j < input.size()-1 - i; ++j){

                if(mode.equals("descending")) {

                    if (input.get(j).getTitle().compareTo(input.get(j + 1).getTitle()) < 0) {
                        temp = input.get(j);
                        input.set(j, input.get(j + 1));
                        input.set(j + 1, temp);
                    }
                }else{
                    if (input.get(j).getTitle().compareTo(input.get(j + 1).getTitle()) > 0) {
                        temp = input.get(j);
                        input.set(j, input.get(j + 1));
                        input.set(j + 1, temp);
                    }
                }

            }

        }

        return input;
    }

    protected void handleFilterAction() {
        System.out.print("Filter set to genre:   ");
        System.out.println(genreComboBox.getValue());

        List<Movie> temp = filter(genreComboBox.getValue(), allMovies, searchField.getText());
        observableMovies.clear();
        observableMovies.addAll(temp);
        movieListView.setItems(observableMovies);
    }

    protected void setUpGenreComboBox() {
        genreComboBox.getItems().addAll(Genre.class.getEnumConstants());
        genreComboBox.setValue(Genre.ALL);
        genreComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> handleFilterAction());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);

        // set data of observable list to list view
        movieListView.setItems(observableMovies);
        // use custom cell factory to display data
        movieListView.setCellFactory(movieListView -> new MovieCell());

        setUpGenreComboBox();

        searchBtn.setOnAction(actionEvent -> handleFilterAction());

        searchField.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)) {

                System.out.print("Enter pressed, searching for:  ");
                System.out.println(searchField.getText());

                // TODO call search method here:
                handleFilterAction();
            }
        });

        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {

                sort("ascending", observableMovies);
                sortBtn.setText("Sort (desc)");
            } else {

                sort("descending", observableMovies);
                sortBtn.setText("Sort (asc)");
            }
        });


    }
}