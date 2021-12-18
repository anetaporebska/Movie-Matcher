package porebska.filmsmatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import porebska.filmsmatcher.model.Movie;
import porebska.filmsmatcher.service.MovieService;

//@CrossOrigin(origins = "http://localhost:63342")
@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id){
        System.out.println("get movie by id");
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping("/admin")
    public String getAdminPage(){
        return "<h1>Welcome Admin </h1>";
    }

    // TODO getMatchingMoviesForUserWithLogin

    // TODO getRandomMovieWithNoPreference

    // TODO getAllMyPreferences

}
