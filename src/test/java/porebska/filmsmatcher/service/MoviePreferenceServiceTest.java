package porebska.filmsmatcher.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import porebska.filmsmatcher.model.Movie;
import porebska.filmsmatcher.model.MoviePreference;
import porebska.filmsmatcher.model.Status;
import porebska.filmsmatcher.model.User;

@SpringBootTest
class MoviePreferenceServiceTest {

    @Autowired
    private MoviePreferenceService moviePreferenceService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @Test
    void addMoviePreference() {

        User user = userService.getUserById(1L);
        Movie movie = movieService.getMovieById(557L);
        MoviePreference moviePreference = MoviePreference.builder()
                .movie(movie)
                .user(user)
                .status(Status.WANT_TO_WATCH)
                .build();
        moviePreferenceService.addMoviePreference(moviePreference);
    }
}