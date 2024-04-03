package xyz.eazywu.music.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import xyz.eazywu.music.exception.BizException;
import xyz.eazywu.music.exception.ExceptionType;
import xyz.eazywu.music.mapper.ArtistMapper;
import xyz.eazywu.music.mapper.MapperInterface;
import xyz.eazywu.music.object.dto.ArtistDto;
import xyz.eazywu.music.object.entity.Artist;
import xyz.eazywu.music.object.filter.ArtistSearchFilter;
import xyz.eazywu.music.repository.ArtistRepository;
import xyz.eazywu.music.repository.search.SearchCriteria;
import xyz.eazywu.music.repository.search.SearchOperation;
import xyz.eazywu.music.repository.search.spec.ArtistSpecification;
import xyz.eazywu.music.service.ArtistService;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArtistServiceImpl extends TraceableServiceImpl<Artist, ArtistDto> implements ArtistService {

    private final ArtistMapper mapper;

    private final ArtistRepository repository;

    @Override
    public Page<ArtistDto> search(ArtistSearchFilter filter) {
        ArtistSpecification spec = new ArtistSpecification();
        spec.add(new SearchCriteria("name", filter.getName(), SearchOperation.MATCH));
        Sort sort = Sort.by(Sort.Direction.ASC, "createdTime");
        Pageable pageable = PageRequest.of(filter.getPage() - 1, filter.getSize(), sort);
        return repository.findAll(spec, pageable).map(mapper::toDto);
    }

    @Override
    public JpaRepository<Artist, String> repository() {
        return repository;
    }

    @Override
    public MapperInterface<Artist, ArtistDto> mapper() {
        return mapper;
    }

    @Override
    public BizException getNotFoundException() {
        return new BizException(ExceptionType.ARTIST_NOT_FOUND);
    }
}
