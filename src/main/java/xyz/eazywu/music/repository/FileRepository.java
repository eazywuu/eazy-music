package xyz.eazywu.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.eazywu.music.object.entity.File;

public interface FileRepository extends JpaRepository<File, String> {
}
