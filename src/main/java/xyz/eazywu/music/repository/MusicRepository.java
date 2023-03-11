package xyz.eazywu.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.eazywu.music.object.entity.MusicEntity;

public interface MusicRepository extends JpaRepository<MusicEntity, String> {
    MusicEntity getMusicById(String id);

}
