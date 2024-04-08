package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label detail = new Label();
    private final Label genre = new Label();
    private final Label releaseYear = new Label();
    private final Label rating = new Label();
    private final VBox layout = new VBox(title, genre, detail, rating, releaseYear);
    private final ImageView imageView = new ImageView();

    public MovieCell() {
        layout.getChildren().add(imageView);
    }

    //ToDo adapt UI Jakob
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
            Image image = new Image(imageUrl);
            imageView.setImage(image);
        }

        this.getStyleClass().add("movie-cell");
        title.setText(movie.getTitle());
        detail.setText(
                movie.getDescription() != null
                        ? movie.getDescription()
                        : "No description available"
        );
        rating.setText(
                movie.getRating() != 0
                ? String.valueOf(movie.getRating())
                : "No rating available"
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
        genre.getStyleClass().add("text-white-cursive");
        layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

        // layout
        title.getFont();
        title.fontProperty().set(Font.font(20));
        if (this.getScene() != null) {
            detail.setMaxWidth(this.getScene().getWidth() - 30);
        }

        detail.setWrapText(true);
        layout.setPadding(new Insets(10));
        layout.spacingProperty().set(10);
        layout.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);
        setGraphic(layout);
    }
}