package xyz.eazywu.music.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.eazywu.music.mapper.MusicMapper;
import xyz.eazywu.music.object.request.MusicCreateReq;
import xyz.eazywu.music.object.request.MusicUpdateReq;
import xyz.eazywu.music.object.vo.MusicVo;
import xyz.eazywu.music.service.MusicService;

@Api(tags = "音乐管理接口")
@RestController
@RequestMapping("/musics")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;

    private final MusicMapper musicMapper;

    @PostMapping
    MusicVo create(@Validated @RequestBody MusicCreateReq musicCreateReq) {
        return musicMapper.toVo(musicService.create(musicCreateReq));
    }

    @PutMapping("/{id}")
    MusicVo update(@PathVariable String id,
                   @Validated @RequestBody MusicUpdateReq musicUpdateReq) {
        return musicMapper.toVo(musicService.update(id, musicUpdateReq));

    }
    @GetMapping
    @ApiOperation("歌曲检索")
    Page<MusicVo> search(@PageableDefault(sort = {"createdTime"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return musicService.search(pageable).map(musicMapper::toVo);
    }

    @PostMapping("/{id}/publish")
    void publish(@PathVariable String id) {
        musicService.publish(id);
    }

    @PostMapping("/{id}/close")
    void close(@PathVariable String id) {
        musicService.close(id);
    }
}
