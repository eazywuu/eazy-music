package xyz.eazywu.music.mapper;

import org.mapstruct.MappingTarget;
import xyz.eazywu.music.object.dto.BaseDto;
import xyz.eazywu.music.object.entity.BaseEntity;

public interface MapperInterface<Entity extends BaseEntity, Dto extends BaseDto> {
    Dto toDto(Entity entity);

    Entity toEntity(Dto dto);

    Entity updateEntity(@MappingTarget Entity entity, Dto dto);
}
