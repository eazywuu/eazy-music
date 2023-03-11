package xyz.eazywu.music.repository;

import xyz.eazywu.music.object.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity getByUsername(String username);

    Optional<UserEntity> findByUsername(String username);

//    User findUserById(String id);

    Page<UserEntity> findAll(Pageable pageable);

}
