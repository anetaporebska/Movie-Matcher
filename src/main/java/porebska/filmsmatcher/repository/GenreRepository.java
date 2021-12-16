package porebska.filmsmatcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import porebska.filmsmatcher.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
