package xyz.eazywu.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.eazywu.music.object.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, String> {
}
