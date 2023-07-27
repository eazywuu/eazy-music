package xyz.eazywu.music.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.eazywu.music.mapper.ArtistMapper;
import xyz.eazywu.music.object.filter.ArtistSearchFilter;
import xyz.eazywu.music.object.request.ArtistCreateReq;
import xyz.eazywu.music.object.request.ArtistUpdateReq;
import xyz.eazywu.music.object.vo.ArtistVo;
import xyz.eazywu.music.service.ArtistService;

@RestController
@RequestMapping("/artists")
@RequiredArgsConstructor
@Slf4j
public class ArtistController {

    private final ArtistService service;

    private final ArtistMapper mapper;

    @GetMapping
    Page<ArtistVo> search(@Validated @ModelAttribute ArtistSearchFilter filter) {
        return service.search(filter).map(mapper::toVo);
    }

    @PostMapping
    public ArtistVo create(@Validated @RequestBody ArtistCreateReq artistCreateReq) {
        return mapper.toVo(service.create(mapper.toDto(artistCreateReq)));
    }
    @PutMapping("/{id}")
    public ArtistVo update(@PathVariable("id") String id,
                           @Validated @RequestBody ArtistUpdateReq artistUpdateReq) {
        return mapper.toVo(service.update(id, mapper.toDto(artistUpdateReq)));
    }
}
