package xyz.eazywu.music.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.eazywu.music.object.request.MusicCreateRequestDto;
import xyz.eazywu.music.object.request.MusicUpdateRequestDto;
import xyz.eazywu.music.mapper.MusicMapper;
import xyz.eazywu.music.service.MusicService;
import xyz.eazywu.music.object.vo.MusicVo;

@Api(tags = "音乐管理接口")
@RestController
@RequestMapping("/musics")
public class MusicController {

    private MusicService musicService;

    private MusicMapper musicMapper;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    MusicVo create(@Validated @RequestBody MusicCreateRequestDto musicCreateRequestDto) {
        return musicMapper.toVo(musicService.create(musicCreateRequestDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    MusicVo update(@PathVariable String id,
                   @Validated @RequestBody MusicUpdateRequestDto musicUpdateRequestDto) {
        return musicMapper.toVo(musicService.update(id, musicUpdateRequestDto));

    }
    @GetMapping
    @ApiOperation("歌曲检索")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Page<MusicVo> search(@PageableDefault(sort = {"createdTime"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return musicService.search(pageable).map(musicMapper::toVo);
    }

    @PostMapping("/publish/{id}")
    void publish(@PathVariable String id) {
        musicService.publish(id);
    }

    @PostMapping("/close/{id}")
    void close(@PathVariable String id) {
        musicService.close(id);
    }

    @Autowired
    public void setMusicService(MusicService musicService) {
        this.musicService = musicService;
    }
    @Autowired
    public void setMusicMapper(MusicMapper musicMapper) {
        this.musicMapper = musicMapper;
    }
}
