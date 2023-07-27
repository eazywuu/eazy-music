package xyz.eazywu.music.service.impl;

import org.springframework.transaction.annotation.Transactional;
import xyz.eazywu.music.object.dto.TraceableDto;
import xyz.eazywu.music.object.entity.TraceableEntity;

/**
 * 文件上传跟踪服务
 * @param <Entity> TraceableEntity跟踪实体
 */
public abstract class TraceableServiceImpl<Entity extends TraceableEntity, Dto extends TraceableDto> extends GeneralServiceImpl<Entity, Dto>{

    @Override
    @Transactional
    public Dto create(Dto dto) {
        Entity entity = mapper().toEntity(dto);
        entity.setCreatedBy(getCurrentUserEntity());
        entity.setUpdatedBy(getCurrentUserEntity());
        return mapper().toDto(repository().save(entity));
    }

    @Override
    @Transactional
    public Dto update(String id, Dto dto) {
        Entity existedEntity = checkExist(id);
        Entity entity = mapper().updateEntity(existedEntity, dto);
        entity.setUpdatedBy(getCurrentUserEntity());
        return mapper().toDto(repository().save(entity));
    }
}
