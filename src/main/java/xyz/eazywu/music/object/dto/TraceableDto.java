package xyz.eazywu.music.object.dto;

import lombok.Data;

@Data
public abstract class TraceableDto extends BaseDto{
    private UserDto createdBy;

    private UserDto updatedBy;
}
