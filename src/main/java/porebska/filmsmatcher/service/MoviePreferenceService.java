package porebska.filmsmatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import porebska.filmsmatcher.model.Movie;
import porebska.filmsmatcher.model.MoviePreference;
import porebska.filmsmatcher.model.Status;
import porebska.filmsmatcher.model.User;
import porebska.filmsmatcher.repository.MoviePreferenceRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MoviePreferenceService {

    @Autowired
    private MoviePreferenceRepository moviePreferenceRepository;

    public MoviePreference addMoviePreference(MoviePreference moviePreference) {
        return moviePreferenceRepository.save(moviePreference);
    }

    public List<MoviePreference> getUserMoviePreferences(User user) {
        return moviePreferenceRepository.findByUser(user);
    }

    public List<Movie> getCommonMovies(User user1, User user2) {
        List<MoviePreference> moviePreferenceList1 = getUserMoviePreferences(user1);
        List<MoviePreference> moviePreferenceList2 = getUserMoviePreferences(user2);
        List<Movie> commonMovies = new ArrayList<>();
        Map<Movie, Status> movieStatusMap1 = new HashMap<>();
        Map<Movie, Status> movieStatusMap2 = new HashMap<>();
        moviePreferenceList1.forEach(preference -> movieStatusMap1.put(preference.getMovie(), preference.getStatus()));
        moviePreferenceList2.forEach(preference -> movieStatusMap2.put(preference.getMovie(), preference.getStatus()));

        for (Map.Entry<Movie, Status> entry : movieStatusMap1.entrySet()) {
            Status st = movieStatusMap2.get(entry.getKey());
            if (st != null && st == entry.getValue() && st.equals(Status.WANT_TO_WATCH)) {
                commonMovies.add(entry.getKey());
            }
        }
        return commonMovies;
    }
}
