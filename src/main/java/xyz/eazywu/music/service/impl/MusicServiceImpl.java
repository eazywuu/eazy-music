package xyz.eazywu.music.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import xyz.eazywu.music.exception.BizException;
import xyz.eazywu.music.exception.ExceptionType;
import xyz.eazywu.music.mapper.MapperInterface;
import xyz.eazywu.music.mapper.MusicMapper;
import xyz.eazywu.music.object.dto.MusicDto;
import xyz.eazywu.music.object.entity.Music;
import xyz.eazywu.music.object.enums.MusicStatusType;
import xyz.eazywu.music.object.filter.MusicSearchFilter;
import xyz.eazywu.music.repository.MusicRepository;
import xyz.eazywu.music.repository.search.SearchCriteria;
import xyz.eazywu.music.repository.search.SearchOperation;
import xyz.eazywu.music.repository.search.spec.MusicSpecification;
import xyz.eazywu.music.service.MusicService;

@Service
@RequiredArgsConstructor
public class MusicServiceImpl extends GeneralServiceImpl<Music, MusicDto> implements MusicService {

    private final MusicRepository repository;

    private final MusicMapper mapper;

    @Override
    public Page<MusicDto> search(MusicSearchFilter filter) {
        MusicSpecification spec = new MusicSpecification();
        spec.add(new SearchCriteria("name", filter.getName(), SearchOperation.MATCH));
        Sort sort = Sort.by(Sort.Direction.ASC, "createdTime");
        Pageable pageable = PageRequest.of(filter.getPage() - 1, filter.getSize(), sort);
        return repository.findAll(spec, pageable).map(mapper::toDto);
    }

    @Override
    public void publish(String id) {
        Music music = super.checkExist(id);
        music.setStatus(MusicStatusType.PUBLISHED);
        repository.save(music);
    }

    @Override
    public void close(String id) {
        Music music = super.checkExist(id);
        music.setStatus(MusicStatusType.CLOSED);
        repository.save(music);
    }

    @Override
    public JpaRepository<Music, String> repository() {
        return repository;
    }

    @Override
    public MapperInterface<Music, MusicDto> mapper() {
        return mapper;
    }

    @Override
    public BizException getNotFoundException() {
        return new BizException(ExceptionType.MUSIC_NOT_FOUND);
    }
}
