package xyz.eazywu.music.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.eazywu.music.mapper.ArtistMapper;
import xyz.eazywu.music.object.request.ArtistCreateReq;
import xyz.eazywu.music.object.request.ArtistUpdateReq;
import xyz.eazywu.music.object.vo.ArtistVo;
import xyz.eazywu.music.service.ArtistService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    private final ArtistMapper artistMapper;

    @GetMapping
    public List<ArtistVo> list() {
        return artistService.list().stream().map(artistMapper::toVo).collect(Collectors.toList());
    }

    @PostMapping
    public ArtistVo create(@Validated @RequestBody ArtistCreateReq artistCreateReq) {
        return artistMapper.toVo(artistService.create(artistCreateReq));
    }
    @PutMapping("/{id}")
    public ArtistVo update(@PathVariable("id") String id,
                           @Validated @RequestBody ArtistUpdateReq artistUpdateReq) {
        return artistMapper.toVo(artistService.update(id, artistUpdateReq));
    }
}
