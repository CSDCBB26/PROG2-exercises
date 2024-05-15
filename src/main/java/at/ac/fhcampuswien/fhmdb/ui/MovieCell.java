package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label detail = new Label();
    private final Label genre = new Label();
    private final Label releaseYear = new Label();
    private final Label rating = new Label();
    private final Label directors = new Label();
    private final Label writers = new Label();
    private final Label mainCast = new Label();
    private final Label lengthInMinutes = new Label();
    private ImageView ratingStar = new ImageView();
    private final HBox ratingBox = new HBox(rating, ratingStar);
    private final HBox headline = new HBox(title, releaseYear, ratingBox);
    private final VBox layout = new VBox(headline, genre, detail, directors, writers, mainCast, lengthInMinutes);
    private final HBox sideBySide = new HBox();
    private final ImageView imageView = new ImageView();
    private final HBox imageContainer = new HBox(imageView); // Wrap imageView in an HBox

    private Map<String, Image> movieTitleToImage = new HashMap<>();
    private final Image RATING_IMG = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXt2Yq6rXpS5eQUE_5bmX-xPezK4ui4ibNF-AAO4sUTA&s");

    private final Button showDetailsButton = new Button("Show Details");
    private final Button addToWatchlistButton = new Button("Add to Watchlist");
    private final Button removeFromWatchlistButton = new Button("Remove from Watchlist");

    private final VBox leftVBox = new VBox(title, genre, detail);
    private final VBox rightVBox = new VBox(showDetailsButton, addToWatchlistButton);
    private final HBox layoutDetail = new HBox(leftVBox, rightVBox);

    public MovieCell() {
        imageView.setFitWidth(500);  // Set the width of the image
        imageView.setFitHeight(500); // Set the height of the image
        ratingStar.setFitWidth(15);  // Set the width of the image
        ratingStar.setFitHeight(15); // Set the height of the image
        imageView.setPreserveRatio(true); // Preserve aspect ratio
        layout.setAlignment(Pos.TOP_RIGHT); // Align items to the right
        headline.setSpacing(100);
        headline.setAlignment(Pos.BOTTOM_LEFT);
        ratingBox.setAlignment(Pos.BOTTOM_RIGHT);
        sideBySide.getChildren().add(layout);
        sideBySide.getChildren().add(imageContainer);

        // Handle button click events
        showDetailsButton.setOnAction(event -> {
            // Show details
        });

        addToWatchlistButton.setOnAction(event -> {
            // Remove/Add to watchlist
        });

        removeFromWatchlistButton.setOnAction(event -> {
            // Remove/Add to watchlist
        });

    }


    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setText(null);
            setGraphic(null);
            imageView.setImage(null);
            return;
        } else {
            String imageUrl = movie.getImgUrl();

            Image image;
            if (movieTitleToImage.containsKey(movie.getTitle())) {
                image = movieTitleToImage.get(movie.getTitle());
            } else {
                image = new Image(imageUrl);
                movieTitleToImage.put(movie.getTitle(), image);
            }

            imageView.setImage(image);
        }

        this.getStyleClass().add("movie-cell");
        title.setText(movie.getTitle());
        releaseYear.setText(
                movie.getReleaseYear() != 0
                        ? String.valueOf(movie.getReleaseYear())
                        : "No release year available"
        );

        lengthInMinutes.setText(
                movie.getLengthInMinutes() != 0
                        ? movie.getLengthInMinutes()/60 + " h " + movie.getLengthInMinutes()%60 + " min"
                        : "No duration available."
        );

        detail.setText(
                movie.getDescription() != null
                        ? movie.getDescription()
                        : "No description available"
        );

        ratingStar.setImage(RATING_IMG);


        rating.setText(
                movie.getRating() != 0
                ? String.valueOf(movie.getRating())
                : "No rating available"
        );
        directors.setText(
                movie.getDirectors() != null && !movie.getDirectors().isEmpty()
                        ? "Directors: " + String.join(", ", movie.getDirectors())
                        : "No director available"
        );
        writers.setText(
                movie.getWriters() != null
                        ? "Writers: " + String.join(", ", movie.getWriters())
                        : "No writer available"
        );
        mainCast.setText(
                movie.getWriters() != null
                        ? "Main Cast: " + String.join(", ", movie.getMainCast())
                        : "No actor available"
        );


        List<Genre> genresAsList = movie.getGenres();
        StringBuilder genresAsText = new StringBuilder(genresAsList.get(0).toString());
        for (int i = 1; i < genresAsList.size(); ++i) {
            genresAsText.append(", ").append(genresAsList.get(i).toString());
        }

        genre.setText(genresAsText.toString());

        // color scheme
        title.getStyleClass().add("text-yellow");
        detail.getStyleClass().add("text-white");
        releaseYear.getStyleClass().add("space-out");
        rating.getStyleClass().add("space-out");
        directors.getStyleClass().add("text-white");
        writers.getStyleClass().add("text-white");
        mainCast.getStyleClass().add("text-white");
        lengthInMinutes.getStyleClass().add("text-white");
        genre.getStyleClass().add("text-white-cursive");
        layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

        // layout
        title.getFont();
        title.fontProperty().set(Font.font(20));

        if (this.getScene() != null) {
            detail.setMaxWidth(this.getScene().getWidth() - 30);
        }

        detail.setPrefWidth(500);

//        sideBySide.setPrefWidth(this.getScene().getWidth());

        detail.setWrapText(true);
        layout.setPadding(new Insets(10));
        layout.spacingProperty().set(10);
        layout.alignmentProperty().set(javafx.geometry.Pos.TOP_LEFT);
        setGraphic(sideBySide);

        // Update layout
        layout.getChildren().clear();
        if (empty || movie == null) {
            layout.getChildren().addAll(new Label("No Movie Available"));
        } else {
            layout.getChildren().addAll(leftVBox, rightVBox);
        }
        setGraphic(layout);
    }
}