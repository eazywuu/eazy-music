package xyz.eazywu.music.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.eazywu.music.exception.BizException;
import xyz.eazywu.music.mapper.MapperInterface;
import xyz.eazywu.music.object.dto.BaseDto;
import xyz.eazywu.music.object.entity.BaseEntity;

public interface GeneralService<Entity extends BaseEntity, Dto extends BaseDto> {
    Page<Dto> search(Pageable pageable);

    Entity checkExist(String id);

    Dto create(Dto dto);

    Dto update(String id, Dto dto);

    void delete(String id);

    JpaRepository<Entity, String> repository();

    MapperInterface<Entity, Dto> mapper();

    BizException getNotFoundException();
}
