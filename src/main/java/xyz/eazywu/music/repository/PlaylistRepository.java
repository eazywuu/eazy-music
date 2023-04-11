package xyz.eazywu.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.eazywu.music.object.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, String> {
}
