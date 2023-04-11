package xyz.eazywu.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.eazywu.music.object.entity.Music;

public interface MusicRepository extends JpaRepository<Music, String> {
    Music getMusicById(String id);

}
