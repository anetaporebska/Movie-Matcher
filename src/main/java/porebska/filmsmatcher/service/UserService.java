package porebska.filmsmatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import porebska.filmsmatcher.model.Movie;
import porebska.filmsmatcher.model.MoviePreference;
import porebska.filmsmatcher.model.User;
import porebska.filmsmatcher.model.UserDetailsImpl;
import porebska.filmsmatcher.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public User getUserByLogin(String login) {
        Optional<User> user = userRepository.findByLogin(login);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + login));
        return user.get();
    }

    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        return user.map(UserDetailsImpl::new).get();
    }

    public void addMoviesWithoutPreferencesForUser(User user, List<Movie> movieList, List<MoviePreference> moviePreferenceList) {
        for (Movie movie : movieList) {
            Optional<MoviePreference> moviePreference = moviePreferenceList.stream().filter(x -> x.getMovie().getMovieId().equals(movie.getMovieId())).findFirst();
            if (moviePreference.isEmpty()) {
                user.setSelectedMovie(movie);
                return;
            }
        }
    }
}
