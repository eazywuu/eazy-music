package xyz.eazywu.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.eazywu.music.object.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
