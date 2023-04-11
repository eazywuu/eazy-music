package xyz.eazywu.music.service;

import xyz.eazywu.music.object.dto.FileUploadDto;

public interface StorageService {
    FileUploadDto initFileUpload();

    String getFileUrl(String fileKey);
}
