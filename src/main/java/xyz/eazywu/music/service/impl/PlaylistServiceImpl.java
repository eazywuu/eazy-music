package xyz.eazywu.music.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.eazywu.music.exception.BizException;
import xyz.eazywu.music.exception.ExceptionType;
import xyz.eazywu.music.mapper.PlaylistMapper;
import xyz.eazywu.music.object.dto.PlaylistDto;
import xyz.eazywu.music.object.entity.Playlist;
import xyz.eazywu.music.repository.PlaylistRepository;
import xyz.eazywu.music.service.PlaylistService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistMapper mapper;

    private final PlaylistRepository repository;

    @Override
    public PlaylistDto get(String id) {
        return mapper.toDto(checkPlaylistExist(id));
    }

    private Playlist checkPlaylistExist(String id) {
        Optional<Playlist> playlist = repository.findById(id);
        if (!playlist.isPresent()) {
            throw new BizException(ExceptionType.PLAYLIST_NOT_FOUND);
        }
        return playlist.get();
    }
}
