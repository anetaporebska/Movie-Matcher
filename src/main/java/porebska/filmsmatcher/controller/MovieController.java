package porebska.filmsmatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import porebska.filmsmatcher.model.Movie;
import porebska.filmsmatcher.service.MovieService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class MovieController {

    // TODO add logger

    @Autowired
    private MovieService movieService;

    @GetMapping("/movie/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id){
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping("/movie")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Movie> getRandomMovie(){
        String currentUserName = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        Movie movie = movieService.getRandomMovie(currentUserName);
        return ResponseEntity.ok(movie);
    }


    // TODO getMatchingMoviesForUserWithLogin

    // TODO getRandomMovieWithNoPreference

    // TODO getAllMyPreferences

}
