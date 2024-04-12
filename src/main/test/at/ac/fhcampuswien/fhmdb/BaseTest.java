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

    protected List<Movie> movieListAll;

    @BeforeEach
    protected void setUp() {
        movieMap = new HashMap<>();
        movieList = new ArrayList<>();
        movieListAll = new ArrayList<>();

        movieMap.put("The Godfather", new Movie.Builder()
                .setTitle("The Godfather")
                .setDescription("The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.")
                .setGenres(List.of(Genre.DRAMA))
                .setReleaseYear(1972)
                .setimgUrl("https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg")
                .setLengthInMinutes(175)
                .setDirectors(List.of("Francis Ford Coppola", "Directore Grande"))
                .setWriters(List.of("Mario Puzo", "Francis Ford Coppola"))
                .setMainCast(List.of("Marlon Brando", "Al Pacino", "James Caan", "Actore Grande"))
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
                .setMainCast(List.of("Tim Robbins", "Morgan Freeman", "Bob Gunton", "Actore Grande"))
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
                .setMainCast(List.of("Christian Bale", "Heath Ledger", "Aaron Eckhart", "Actore Grande"))
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

        movieMap.put("Seven", new Movie.Builder()
                .setTitle("Seven")
                .setDescription("Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his modus operandi.")
                .setGenres(List.of(Genre.DRAMA, Genre.CRIME, Genre.MYSTERY, Genre.THRILLER))
                .setReleaseYear(1995)
                .setimgUrl("https://m.media-amazon.com/images/M/MV5BOTUwODM5MTctZjczMi00OTk4LTg3NWUtNmVhMTAzNTNjYjcyXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_FMjpg_UX1000_.jpg")
                .setLengthInMinutes(127)
                .setDirectors(List.of("David Fincher"))
                .setWriters(List.of("Andrew Kevin Walker"))
                .setMainCast(List.of("Morgan Freeman", "Brad Pitt", "Kevin Spacey"))
                .setRating(8.6)
                .build());

        movieList = new ArrayList<>(movieMap.values());

        movieListAll = List.of(
                new Movie("The Godfather", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", List.of(Genre.DRAMA)),
                new Movie("The Shawshank Redemption", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", List.of(Genre.DRAMA)),
                new Movie("The Dark Knight", "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, the caped crusader must come to terms with one of the greatest psychological tests of his ability to fight injustice.", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA)),
                new Movie("Schindler's List", "In German-occupied Poland during World War II, Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazi Germans.", List.of(Genre.BIOGRAPHY, Genre.DRAMA, Genre.HISTORY)),
                new Movie("Pulp Fiction", "The lives of two mob hit men, a boxer, a gangster's wife, and a pair of diner bandits intertwine in four tales of violence and redemption.", List.of(Genre.CRIME, Genre.DRAMA)),
                new Movie("The Lord of the Rings: The Return of the King", "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.", List.of(Genre.ADVENTURE, Genre.DRAMA, Genre.FANTASY)),
                new Movie("Star Wars: Episode V - The Empire Strikes Back", "After the rebels are brutally overpowered by the Empire on the ice planet Hoth, Luke Skywalker begins Jedi training with Yoda, while his friends are pursued by Darth Vader and a bounty hunter named Boba Fett all over the galaxy.", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY, Genre.SCIENCE_FICTION)),
                new Movie("The Good, the Bad and the Ugly", "A bounty hunting scam joins two men in an uneasy alliance against a third in a race to find a fortune in gold buried in a remote cemetery.", List.of(Genre.WESTERN)),
                new Movie("12 Angry Men", "A jury holdout attempts to prevent a miscarriage of justice by forcing his colleagues to reconsider the evidence.", List.of(Genre.DRAMA)),
                new Movie("The Lord of the Rings: The Two Towers", "While Frodo and Sam edge closer to Mordor with the help of the shifty Gollum, the divided fellowship makes a stand against Sauron\"s new ally, Saruman, and his hordes of Isengard.", List.of(Genre.ADVENTURE, Genre.DRAMA, Genre.FANTASY)),
                new Movie("One Flew Over the Cuckoo\"s Nest", "A criminal pleads insanity after getting into trouble again and once in the mental institution rebels against the oppressive nurse and rallies up the scared patients.", List.of(Genre.DRAMA)),
                new Movie("Inception", "A thief, who steals corporate secrets through use of dream-sharing technology, is given the inverse task of planting an idea into the mind of a CEO.", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION, Genre.THRILLER)),
                new Movie("Goodfellas", "The story of Henry Hill and his life in the mob, covering his relationship with his wife Karen Hill and his mob partners Jimmy Conway and Tommy DeVito in the Italian-American crime syndicate.", List.of(Genre.BIOGRAPHY, Genre.CRIME, Genre.DRAMA)),
                new Movie("The Matrix", "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.", List.of(Genre.ACTION, Genre.SCIENCE_FICTION)),
                new Movie("Seven", "Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his modus operandi.", List.of(Genre.CRIME, Genre.DRAMA, Genre.MYSTERY, Genre.THRILLER)),
                new Movie("The Silence of the Lambs", "A young FBI cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer, a madman who skins his victims.", List.of(Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                new Movie("It\"s a Wonderful Life", "An angel is sent from Heaven to help a desperately frustrated businessman by showing him what life would have been like if he had never existed.", List.of(Genre.DRAMA, Genre.FAMILY, Genre.FANTASY)),
                new Movie("Saving Private Ryan", "Following the Normandy Landings, a group of U.S. soldiers go behind enemy lines to retrieve a paratrooper whose brothers have been killed in action.", List.of(Genre.ACTION, Genre.DRAMA, Genre.WAR)),
                new Movie("City of God", "Two boys growing up in a violent neighborhood of Rio de Janeiro take different paths: one becomes a photographer, the other a drug dealer.", List.of(Genre.CRIME, Genre.DRAMA)),
                new Movie("Life Is Beautiful", "When an open-minded Jewish librarian and his son become victims of the Holocaust, he uses a perfect mixture of will, humor, and imagination to protect his son from the dangers around their camp.", List.of(Genre.COMEDY, Genre.DRAMA, Genre.ROMANCE, Genre.WAR)),
                new Movie("The Usual Suspects", "A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat, which begin when five criminals meet at a seemingly random police lineup.", List.of(Genre.CRIME, Genre.MYSTERY, Genre.THRILLER)),
                new Movie("Puss in Boots", "An outlaw cat, his childhood egg-friend, and a seductive thief kitty set out in search for the eggs of the fabled Golden Goose to clear his name, restore his lost honor, and regain the trust of his mother and town.", List.of(Genre.ANIMATION, Genre.ADVENTURE, Genre.COMEDY)),
                new Movie("Forrest Gump", "The presidencies of Kennedy and Johnson, the Vietnam War, the Watergate scandal and other historical events unfold from the perspective of an Alabama man with an IQ of 75, whose only desire is to be reunited with his childhood sweetheart.", List.of(Genre.DRAMA, Genre.ROMANCE)),
                new Movie("The Lion King", "Lion cub and future king Simba searches for his identity. His eagerness to please others and penchant for testing his boundaries sometimes gets him into trouble.", List.of(Genre.ANIMATION, Genre.ADVENTURE, Genre.DRAMA, Genre.FAMILY, Genre.MUSICAL)),
                new Movie("Spirited Away", "During her family's move to the suburbs, a sullen 10-year-old girl wanders into a world ruled by gods, witches, and spirits, and where humans are changed into beasts.", List.of(Genre.ANIMATION, Genre.ADVENTURE, Genre.FAMILY, Genre.FANTASY, Genre.MYSTERY)),
                new Movie("Toy Story", "A cowboy doll is profoundly threatened and jealous when a new spaceman action figure supplants him as top toy in a boy's bedroom.", List.of(Genre.ANIMATION, Genre.ADVENTURE, Genre.COMEDY, Genre.FAMILY, Genre.FANTASY)),
                new Movie("Avatar", "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY, Genre.SCIENCE_FICTION)),
                new Movie("Knives Out", "A detective investigates the death of a patriarch of an eccentric, combative family.", List.of(Genre.COMEDY, Genre.CRIME, Genre.DRAMA, Genre.MYSTERY, Genre.THRILLER)),
                new Movie("Once Upon a Time in Hollywood", "A faded television actor and his stunt double strive to achieve fame and success in the film industry during the final years of Hollywood's Golden Age in 1969 Los Angeles.", List.of(Genre.COMEDY, Genre.DRAMA)),
                new Movie("Django Unchained", "With the help of a German bounty hunter, a freed slave sets out to rescue his wife from a brutal Mississippi plantation owner.", List.of(Genre.DRAMA, Genre.WESTERN)),
                new Movie("The Wolf of Wall Street", "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.", List.of(Genre.BIOGRAPHY, Genre.COMEDY, Genre.CRIME, Genre.DRAMA))
        );
    }
}