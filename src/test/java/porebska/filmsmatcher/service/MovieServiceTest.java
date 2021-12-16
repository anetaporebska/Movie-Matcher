package porebska.filmsmatcher.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import porebska.filmsmatcher.model.Genre;
import porebska.filmsmatcher.model.Movie;

import java.util.*;

@SpringBootTest
class MovieServiceTest {

    @Autowired
    MovieService movieService;

    @Autowired
    GenreService genreService;

    @Test
    void addMovie() {

        List<Genre> genres = new ArrayList<>();
        genres.add(genreService.getGenreById(18L).orElseThrow(() -> { throw new RuntimeException("no genre with id"); }));
        genres.add(genreService.getGenreById(80L).orElseThrow(() -> { throw new RuntimeException("no genre with id"); }));

        Movie movie1 = new Movie(278L, "The Shawshank Redemption", "1994-09-23", 8.7, genres);
        movieService.addMovie(movie1);
    }
}