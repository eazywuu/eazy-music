package xyz.eazywu.music.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.eazywu.music.mapper.PlaylistMapper;
import xyz.eazywu.music.object.vo.PlaylistVo;
import xyz.eazywu.music.service.PlaylistService;

@RestController
@RequestMapping("/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    private final PlaylistMapper playlistMapper;

    @GetMapping("/{id}")
    public PlaylistVo get(@PathVariable("id") String id) {
        return playlistMapper.toVo(playlistService.get(id));
    }
}
