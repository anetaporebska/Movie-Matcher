package porebska.filmsmatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import porebska.filmsmatcher.model.Movie;
import porebska.filmsmatcher.model.MoviePreference;
import porebska.filmsmatcher.model.Status;
import porebska.filmsmatcher.model.User;
import porebska.filmsmatcher.payload.response.MessageResponse;
import porebska.filmsmatcher.service.MoviePreferenceService;
import porebska.filmsmatcher.service.MovieService;
import porebska.filmsmatcher.service.UserService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class MovieController {

    // TODO add logger

    @Autowired
    private MovieService movieService;

    @Autowired
    private MoviePreferenceService moviePreferenceService;

    @Autowired
    private UserService userService;

    @GetMapping("/movie")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Movie> getRandomMovie() {
        User user = getCurrentUser();
        List<MoviePreference> moviePreferenceList = moviePreferenceService.getUserMoviePreferences(user);
        List<Movie> movieList = movieService.getAllMovies();
        userService.addMoviesWithoutPreferencesForUser(user, movieList, moviePreferenceList);
        Movie movie = user.getSelectedMovie();
        return ResponseEntity.ok(movie);
    }

    @PostMapping("/movie/{id}/{p}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addPreference(@PathVariable Long id, @PathVariable int p) {
        User user = getCurrentUser();
        MoviePreference moviePreference = MoviePreference.builder()
                .movie(movieService.getMovieById(id))
                .user(user)
                .status(Status.intToStatus(p))
                .build();
        System.out.println(moviePreference);
        moviePreferenceService.addMoviePreference(moviePreference);
        return ResponseEntity.ok(new MessageResponse("Preference added"));
    }

    @GetMapping("/movie/{user}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getMatchingMovies(@PathVariable String user) {
        User currentUser = getCurrentUser();
        User requestedUser = userService.getUserByLogin(user);
        List<Movie> commonMovieList = moviePreferenceService.getCommonMovies(currentUser, requestedUser);
        return ResponseEntity.ok(commonMovieList);
    }


    private User getCurrentUser() {
        String currentUserName = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return userService.getUserByLogin(currentUserName);
    }
}
