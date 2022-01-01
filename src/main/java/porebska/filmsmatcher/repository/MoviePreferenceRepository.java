package porebska.filmsmatcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import porebska.filmsmatcher.model.MoviePreference;
import porebska.filmsmatcher.model.User;

import java.util.List;

@Repository
public interface MoviePreferenceRepository extends JpaRepository<MoviePreference, Long> {
    List<MoviePreference> findByUser(User user);
}
