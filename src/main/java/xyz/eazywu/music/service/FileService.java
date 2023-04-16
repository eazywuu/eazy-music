package xyz.eazywu.music.service;

import xyz.eazywu.music.object.dto.FileDto;
import xyz.eazywu.music.object.dto.FileUploadDto;
import xyz.eazywu.music.object.entity.File;
import xyz.eazywu.music.object.enums.StorageType;
import xyz.eazywu.music.object.request.FileUploadReq;

public interface FileService {
    FileUploadDto initUpload(FileUploadReq fileUploadReq);

    FileDto finishUpload(String id);

    File getFileEntity(String id);

    StorageType getDefaultStorage();
}
