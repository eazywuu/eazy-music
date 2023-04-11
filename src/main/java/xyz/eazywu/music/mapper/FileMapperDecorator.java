package xyz.eazywu.music.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import xyz.eazywu.music.object.dto.FileDto;
import xyz.eazywu.music.object.entity.File;
import xyz.eazywu.music.service.StorageService;

import java.util.Map;

@Slf4j
public abstract class FileMapperDecorator implements FileMapper{

    @Autowired
    @Qualifier("delegate")
    private FileMapper delegate;

    @Autowired
    private Map<String, StorageService> storageServiceMap;


    @Override
    public FileDto toDto(File file) {
        FileDto fileDto = delegate.toDto(file);

        if (fileDto == null) {
            return null;
        }
        if (fileDto.getStorage() == null) {
            return null;
        }
        // TODO: 从cos获取临时url
        fileDto.setUrl(storageServiceMap.get(fileDto.getStorage().name()).getFileUrl(fileDto.getKey()));
        return fileDto;
    }
}
