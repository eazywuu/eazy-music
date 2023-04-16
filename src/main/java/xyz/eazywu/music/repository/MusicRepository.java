package xyz.eazywu.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xyz.eazywu.music.object.entity.Music;

public interface MusicRepository extends JpaRepository<Music, String>, JpaSpecificationExecutor<Music> {
}
