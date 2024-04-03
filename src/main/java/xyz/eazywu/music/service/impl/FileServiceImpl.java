package xyz.eazywu.music.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.eazywu.music.exception.BizException;
import xyz.eazywu.music.exception.ExceptionType;
import xyz.eazywu.music.mapper.FileMapper;
import xyz.eazywu.music.object.dto.FileDto;
import xyz.eazywu.music.object.dto.FileUploadDto;
import xyz.eazywu.music.object.entity.File;
import xyz.eazywu.music.object.entity.User;
import xyz.eazywu.music.object.enums.FileStatusType;
import xyz.eazywu.music.object.enums.StorageType;
import xyz.eazywu.music.object.request.FileUploadReq;
import xyz.eazywu.music.repository.FileRepository;
import xyz.eazywu.music.service.FileService;
import xyz.eazywu.music.service.StorageService;
import xyz.eazywu.music.utils.FileTypeTransformer;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl extends UserContextService implements FileService {

    private final Map<String, StorageService> storageServiceMap;

    private final FileRepository fileRepository;

    private final FileMapper fileMapper;

    @Override
    @Transactional
    public FileUploadDto initUpload(FileUploadReq fileUploadReq) {
        File file = fileMapper.createEntity(fileUploadReq);
        file.setType(FileTypeTransformer.getFileTypeFromExt(fileUploadReq.getExt()));
        file.setStorage(getDefaultStorage());
        file.setCreatedBy(getCurrentUserEntity());
        file.setUpdatedBy(getCurrentUserEntity());
        // 数据库存储file信息
        File savedFile = fileRepository.save(file);
        // 通过接口获取STS令牌，default为cos厂商提供的对象存储
        FileUploadDto fileUploadDto = storageServiceMap.get(getDefaultStorage().name()).initFileUpload();
        // 注入file-id
        fileUploadDto.setFileId(savedFile.getId());
        // 注入hash-key
        fileUploadDto.setFileKey(savedFile.getKey());

        return fileUploadDto;
    }

    @Override
    public FileDto finishUpload(String id) {
        // TODO: 是否是SUPER_ADMIN
        // TODO: 验证远程文件是否存在

        File file = getFileEntity(id);
        User user = getCurrentUserEntity();
        // TODO: 只有上传者和超级管理员才能更新finish; 权限判断
        if (!Objects.equals(user.getRoles().get(1).getName(), "ROLE_ADMIN") && file.getCreatedBy().getId() != user.getId()) {
            throw new BizException(ExceptionType.FILE_NOT_PERMISSION);
        }

        file.setStatus(FileStatusType.UPLOADED);
        return fileMapper.toDto(fileRepository.save(file));
    }

    @Override
    public File getFileEntity(String id) {
        Optional<File> fileOptional = fileRepository.findById(id);
        if(!fileOptional.isPresent()) {
            throw new BizException(ExceptionType.FILE_NOT_FOUND);
        }
        return fileOptional.get();
    }

    @Override
    // Todo: 后台设置当前Storage
    public StorageType getDefaultStorage() {
        return StorageType.COS;
    }

}
