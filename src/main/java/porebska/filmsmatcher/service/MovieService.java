package porebska.filmsmatcher.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import porebska.filmsmatcher.model.Movie;
import porebska.filmsmatcher.model.User;
import porebska.filmsmatcher.repository.MovieRepository;
import porebska.filmsmatcher.repository.UserRepository;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MoviePreferenceService moviePreferenceService;

    @Autowired
    private UserService userService;

    public Movie addMovie(Movie movie){
        if (movieRepository.existsById(movie.getMovieId())){
            return null;
        }
        return movieRepository.save(movie);
    }

    public Movie getMovieById(Long id){
        return movieRepository.getById(id);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public List<Movie> getAllMoviesByCategory(String category){
        return null; // TODO
    }


    public Movie getRandomMovie(String login) {
        User user = userService.getUserByLogin(login);
        System.out.println(moviePreferenceService.getUserMoviePreferences(user));
        return null;
    }
}
