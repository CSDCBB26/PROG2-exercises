package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseTest {
    protected List<Movie> movieList;

    protected Map<String, Movie> movieMap;

    @BeforeEach
    protected void setUp() {
        movieMap = new HashMap<>();
        movieList = new ArrayList<>();

        movieMap.put("The Godfather", new Movie.Builder()
                .setTitle("The Godfather")
                .setDescription("The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.")
                .setGenres(List.of(Genre.DRAMA))
                .setReleaseYear(1972)
                .setimgUrl("https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg")
                .setLengthInMinutes(175)
                .setDirectors(List.of("Francis Ford Coppola", "Directore Grande"))
                .setWriters(List.of("Mario Puzo", "Francis Ford Coppola"))
                .setMainCast(List.of("Marlon Brando", "Al Pacino", "James Caan"))
                .setRating(9.2)
                .build());

        movieMap.put("The Shawshank Redemption", new Movie.Builder()
                .setTitle("The Shawshank Redemption")
                .setDescription("Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.")
                .setGenres(List.of(Genre.DRAMA))
                .setReleaseYear(1994)
                .setimgUrl("https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@._V1_.jpg")
                .setLengthInMinutes(142)
                .setDirectors(List.of("Frank Darabont", "Directore Grande"))
                .setWriters(List.of("Stephen King", "Frank Darabont"))
                .setMainCast(List.of("Tim Robbins", "Morgan Freeman", "Bob Gunton"))
                .setRating(9.3)
                .build());

        movieMap.put("The Dark Knight", new Movie.Builder()
                .setTitle("The Dark Knight")
                .setDescription("When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, the caped crusader must come to terms with one of the greatest psychological tests of his ability to fight injustice.")
                .setGenres(List.of(Genre.DRAMA, Genre.ACTION, Genre.CRIME))
                .setReleaseYear(2008)
                .setimgUrl("https://m.media-amazon.com/images/M/MV5BMTk4ODQzNDY3Ml5BMl5BanBnXkFtZTcwODA0NTM4Nw@@._V1_FMjpg_UX1000_.jpg")
                .setLengthInMinutes(152)
                .setDirectors(List.of("Christopher Nolan", "Directore Grande"))
                .setWriters(List.of("Jonathan Nolan", "Christopher Nolan"))
                .setMainCast(List.of("Christian Bale", "Heath Ledger", "Aaron Eckhart"))
                .setRating(9.0)
                .build());

        movieMap.put("Schindler's List", new Movie.Builder()
                .setTitle("Schindler's List")
                .setDescription("In German-occupied Poland during World War II, Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazi Germans.")
                .setGenres(List.of(Genre.BIOGRAPHY, Genre.DRAMA, Genre.HISTORY))
                .setReleaseYear(1993)
                .setimgUrl("https://m.media-amazon.com/images/M/MV5BNDE4OTMxMTctNmRhYy00NWE2LTg3YzItYTk3M2UwOTU5Njg4XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_.jpg")
                .setLengthInMinutes(195)
                .setDirectors(List.of("Steven Spielberg"))
                .setWriters(List.of("Steven Zaillian", "Thomas Keneally"))
                .setMainCast(List.of("Liam Neeson", "Ralph Fiennes", "Ben Kingsley"))
                .setRating(8.9)
                .build());

        movieMap.put("Pulp Fiction", new Movie.Builder()
                .setTitle("Pulp Fiction")
                .setDescription("The lives of two mob hit men, a boxer, a gangster's wife, and a pair of diner bandits intertwine in four tales of violence and redemption.")
                .setGenres(List.of(Genre.CRIME, Genre.DRAMA))
                .setReleaseYear(1994)
                .setimgUrl("https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg")
                .setLengthInMinutes(154)
                .setDirectors(List.of("Quentin Tarantino"))
                .setWriters(List.of("Quentin Tarantino", "Roger Avary"))
                .setMainCast(List.of("John Travolta", "Uma Thurman", "Samuel L. Jackson"))
                .setRating(8.9)
                .build());

        movieMap.put("Once Upon a Time in Hollywood", new Movie.Builder()
                .setTitle("Once Upon a Time in Hollywood")
                .setDescription("A faded television actor and his stunt double strive to achieve fame and success in the film industry during the final years of Hollywood's Golden Age in 1969 Los Angeles.")
                .setGenres(List.of(Genre.COMEDY, Genre.DRAMA))
                .setReleaseYear(2019)
                .setimgUrl("https://m.media-amazon.com/images/M/MV5BOTg4ZTNkZmUtMzNlZi00YmFjLTk1MmUtNWQwNTM0YjcyNTNkXkEyXkFqcGdeQXVyNjg2NjQwMDQ@._V1_UY1200_CR90,0,630,1200_AL_.jpg")
                .setLengthInMinutes(161)
                .setDirectors(List.of("Quentin Tarantino"))
                .setWriters(List.of("Quentin Tarantino"))
                .setMainCast(List.of("Leonardo DiCaprio", "Brad Pitt", "Margot Robbie"))
                .setRating(7.7)
                .build());

        movieMap.put("The Lord of the Rings: The Return of the King", new Movie.Builder()
                .setTitle("The Lord of the Rings: The Return of the King")
                .setDescription("Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.")
                .setGenres(List.of(Genre.ADVENTURE, Genre.DRAMA, Genre.FANTASY))
                .setReleaseYear(2003)
                .setimgUrl("https://m.media-amazon.com/images/M/MV5BNzA5ZDNlZWMtM2NhNS00NDJjLTk4NDItYTRmY2EwMWZlMTY3XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_FMjpg_UX1000_.jpg")
                .setLengthInMinutes(201)
                .setDirectors(List.of("Peter Jackson"))
                .setWriters(List.of("J.R.R. Tolkien", "Fran Walsh", "Philippa Boyens"))
                .setMainCast(List.of("Elijah Wood", "Ian McKellen", "Viggo Mortensen"))
                .setRating(8.9)
                .build());

        movieMap.put("Star Wars: Episode V - The Empire Strikes Back", new Movie.Builder()
                .setTitle("Star Wars: Episode V - The Empire Strikes Back")
                .setDescription("After the rebels are brutally overpowered by the Empire on the ice planet Hoth, Luke Skywalker begins Jedi training with Yoda, while his friends are pursued by Darth Vader and a bounty hunter named Boba Fett all over the galaxy.")
                .setGenres(List.of(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY, Genre.SCIENCE_FICTION))
                .setReleaseYear(1980)
                .setimgUrl("https://m.media-amazon.com/images/M/MV5BYmU1NDRjNDgtMzhiMi00NjZmLTg5NGItZDNiZjU5NTU4OTE0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg")
                .setLengthInMinutes(124)
                .setDirectors(List.of("Irvin Kershner"))
                .setWriters(List.of("Leigh Brackett", "Lawrence Kasdan", "George Lucas"))
                .setMainCast(List.of("Mark Hamill", "Harrison Ford", "Carrie Fisher"))
                .setRating(8.7)
                .build());

        movieMap.put("The Good, the Bad and the Ugly", new Movie.Builder()
                .setTitle("The Good, the Bad and the Ugly")
                .setDescription("A bounty hunting scam joins two men in an uneasy alliance against a third in a race to find a fortune in gold buried in a remote cemetery.")
                .setGenres(List.of(Genre.WESTERN))
                .setReleaseYear(1966)
                .setimgUrl("https://m.media-amazon.com/images/M/MV5BNjJlYmNkZGItM2NhYy00MjlmLTk5NmQtNjg1NmM2ODU4OTMwXkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_FMjpg_UX1000_.jpg")
                .setLengthInMinutes(161)
                .setDirectors(List.of("Sergio Leone"))
                .setWriters(List.of("Luciano Vincenzoni", "Agenore Incrocci", "Furio Scarpelli", "Sergio Leone"))
                .setMainCast(List.of("Clint Eastwood", "Eli Wallach", "Lee Van Cleef"))
                .setRating(8.9)
                .build());

        movieMap.put("12 Angry Men", new Movie.Builder()
                .setTitle("12 Angry Men")
                .setDescription("A jury holdout attempts to prevent a miscarriage of justice by forcing his colleagues to reconsider the evidence.")
                .setGenres(List.of(Genre.DRAMA))
                .setReleaseYear(1957)
                .setimgUrl("https://m.media-amazon.com/images/M/MV5BMWU4N2FjNzYtNTVkNC00NzQ0LTg0MjAtYTJlMjFhNGUxZDFmXkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_FMjpg_UX1000_.jpg")
                .setLengthInMinutes(96)
                .setDirectors(List.of("Sidney Lumet"))
                .setWriters(List.of("Reginald Rose"))
                .setMainCast(List.of("Henry Fonda", "Lee J. Cobb", "Martin Balsam"))
                .setRating(8.9)
                .build());

        movieList = new ArrayList<>(movieMap.values());

    }
}
