package porebska.filmsmatcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import porebska.filmsmatcher.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
