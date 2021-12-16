package porebska.filmsmatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import porebska.filmsmatcher.model.MoviePreference;
import porebska.filmsmatcher.repository.MoviePreferenceRepository;

@Service
public class MoviePreferenceService {

    @Autowired
    private MoviePreferenceRepository moviePreferenceRepository;

    public MoviePreference addMoviePreference(MoviePreference moviePreference){
        return moviePreferenceRepository.save(moviePreference);
    }


}
