package porebska.filmsmatcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import porebska.filmsmatcher.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

    boolean existsByLogin(String username);
}
