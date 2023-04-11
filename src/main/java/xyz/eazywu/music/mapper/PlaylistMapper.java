package xyz.eazywu.music.mapper;

import org.mapstruct.Mapper;
import xyz.eazywu.music.object.dto.PlaylistDto;
import xyz.eazywu.music.object.entity.Playlist;
import xyz.eazywu.music.object.vo.PlaylistVo;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
    PlaylistDto toDto(Playlist playlist);

    PlaylistVo toVo(PlaylistDto playlistDto);
}
