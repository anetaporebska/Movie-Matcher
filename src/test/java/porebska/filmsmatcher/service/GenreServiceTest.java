package porebska.filmsmatcher.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import porebska.filmsmatcher.model.Genre;

@SpringBootTest
class GenreServiceTest {

    @Autowired
    GenreService genreService;

    @Test
    void addGenre() {

        Genre genre1 = new Genre(18L, "Drama");
        Genre genre2 = new Genre(80L, "Crime");
        genreService.addGenre(genre1);
        genreService.addGenre(genre2);
        System.out.println(genreService.getAllGenres());
    }
}