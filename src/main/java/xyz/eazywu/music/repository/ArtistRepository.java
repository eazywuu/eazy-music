package xyz.eazywu.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.eazywu.music.object.entity.Artist;

public interface ArtistRepository extends JpaRepository<Artist, String> {
}
