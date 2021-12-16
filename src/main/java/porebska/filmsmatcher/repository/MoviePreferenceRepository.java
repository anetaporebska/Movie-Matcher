package porebska.filmsmatcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import porebska.filmsmatcher.model.MoviePreference;

@Repository
public interface MoviePreferenceRepository extends JpaRepository<MoviePreference, Long> {
}
