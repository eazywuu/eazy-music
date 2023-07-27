package xyz.eazywu.music.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import xyz.eazywu.music.object.dto.BaseDto;
import xyz.eazywu.music.object.entity.BaseEntity;
import xyz.eazywu.music.service.GeneralService;

import java.util.Optional;

/**
 * 通用Service实现类,包括增、删、改、查和判断是否存在方法
 * @param <Entity> 实体
 * @param <Dto> Dto映射
 * @extends <UserContextService> 用户上下文
 */
public abstract class GeneralServiceImpl<Entity extends BaseEntity, Dto extends BaseDto> extends UserContextService implements GeneralService<Entity, Dto> {

    public Page<Dto> search(Pageable pageable) {
        return repository().findAll(pageable).map(mapper()::toDto);
    }

    @Transactional
    public Dto create(Dto dto){
        return mapper().toDto(repository().save(mapper().toEntity(dto)));
    }

    @Transactional
    public Dto update(String id, Dto dto){
        // Todo: dto可能无法控制更新字段
        return mapper().toDto(repository().save(mapper().updateEntity(checkExist(id), dto)));
    }

    @Transactional
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
