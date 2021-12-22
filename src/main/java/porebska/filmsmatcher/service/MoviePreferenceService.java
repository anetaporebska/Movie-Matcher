package porebska.filmsmatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import porebska.filmsmatcher.model.Movie;
import porebska.filmsmatcher.model.MoviePreference;
import porebska.filmsmatcher.model.User;
import porebska.filmsmatcher.repository.MoviePreferenceRepository;

import java.util.List;

@Service
public class MoviePreferenceService {

    @Autowired
    private MoviePreferenceRepository moviePreferenceRepository;

    public MoviePreference addMoviePreference(MoviePreference moviePreference){
        return moviePreferenceRepository.save(moviePreference);
    }

    public List<MoviePreference> getUserMoviePreferences(User user){
        return moviePreferenceRepository.findByUser(user);
    }

}
