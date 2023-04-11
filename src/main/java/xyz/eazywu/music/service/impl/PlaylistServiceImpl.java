package xyz.eazywu.music.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.eazywu.music.exception.BizException;
import xyz.eazywu.music.exception.ResultType;
import xyz.eazywu.music.mapper.PlaylistMapper;
import xyz.eazywu.music.object.dto.PlaylistDto;
import xyz.eazywu.music.object.entity.Playlist;
import xyz.eazywu.music.repository.PlaylistRepository;
import xyz.eazywu.music.service.PlaylistService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistMapper playlistMapper;

    private final PlaylistRepository playlistRepository;

    @Override
    public PlaylistDto get(String id) {
        return playlistMapper.toDto(checkPlaylistExist(id));
    }

    private Playlist checkPlaylistExist(String id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);
        if (!playlist.isPresent()) {
            throw new BizException(ResultType.PLAYLIST_NOT_FOUND);
        }
        return playlist.get();
    }
}
