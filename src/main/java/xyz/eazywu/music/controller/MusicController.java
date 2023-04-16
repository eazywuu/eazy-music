package xyz.eazywu.music.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.eazywu.music.mapper.MusicMapper;
import xyz.eazywu.music.object.request.MusicCreateReq;
import xyz.eazywu.music.object.request.MusicSearchFilter;
import xyz.eazywu.music.object.request.MusicUpdateReq;
import xyz.eazywu.music.object.vo.MusicVo;
import xyz.eazywu.music.service.MusicService;

import java.util.Map;

@Api(tags = "音乐管理接口")
@RestController
@RequestMapping("/musics")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
@Slf4j
public class MusicController {

    private final MusicService musicService;

    private final MusicMapper musicMapper;

    @GetMapping
    @ApiOperation("歌曲检索")
    Page<MusicVo> list(@RequestParam Map<String, Object> map) {
        log.info(map.toString());
        MusicSearchFilter filter = new MusicSearchFilter();
        filter.setName(map.get("name").toString());
        filter.setPage(Integer.parseInt(map.get("page").toString()));
        filter.setSize(Integer.parseInt(map.get("size").toString()));
        return musicService.search(filter).map(musicMapper::toVo);
    }

    @PostMapping
    MusicVo create(@Validated @RequestBody MusicCreateReq musicCreateReq) {
        return musicMapper.toVo(musicService.create(musicMapper.toDto(musicCreateReq)));
    }

    @PutMapping("/{id}")
    MusicVo update(@PathVariable String id,
                   @Validated @RequestBody MusicUpdateReq musicUpdateReq) {
        return musicMapper.toVo(musicService.update(id, musicMapper.toDto(musicUpdateReq)));
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
