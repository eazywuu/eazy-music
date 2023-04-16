package xyz.eazywu.music.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import xyz.eazywu.music.object.dto.BaseDto;
import xyz.eazywu.music.object.entity.BaseEntity;
import xyz.eazywu.music.service.GeneralService;

import java.util.Optional;

public abstract class GeneralServiceImpl<Entity extends BaseEntity, Dto extends BaseDto> implements GeneralService<Entity, Dto> {

    public Page<Dto> list(Pageable pageable) {
        return repository().findAll(pageable).map(mapper()::toDto);
    }

    public Dto create(Dto dto){
        return mapper().toDto(repository().save(mapper().toEntity(dto)));
    }

    @Transactional
    public Dto update(String id, Dto dto){
        // ToDo: dto可能无法控制更新字段
        return mapper().toDto(repository().save(mapper().updateEntity(checkExist(id), dto)));
    }

    public void delete(String id){
        repository().delete(checkExist(id));
    }

    public Entity checkExist(String id) {
        Optional<Entity> optionalEntity = repository().findById(id);
        if (!optionalEntity.isPresent()) {
            throw getNotFoundException();
        }
        return optionalEntity.get();
    }
}
