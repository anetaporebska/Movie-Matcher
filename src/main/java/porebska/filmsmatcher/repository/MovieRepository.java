package porebska.filmsmatcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import porebska.filmsmatcher.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
