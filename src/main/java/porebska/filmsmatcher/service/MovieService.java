package porebska.filmsmatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import porebska.filmsmatcher.model.Movie;
import porebska.filmsmatcher.repository.MovieRepository;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MoviePreferenceService moviePreferenceService;

    public Movie addMovie(Movie movie) {
        if (movieRepository.existsById(movie.getMovieId())) {
            return null;
        }
        return movieRepository.save(movie);
    }

    public Movie getMovieById(Long id) {
        return movieRepository.getById(id);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
