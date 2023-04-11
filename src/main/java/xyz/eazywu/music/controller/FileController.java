package xyz.eazywu.music.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.eazywu.music.mapper.FileMapper;
import xyz.eazywu.music.mapper.FileUploadMapper;
import xyz.eazywu.music.object.request.FileUploadReq;
import xyz.eazywu.music.object.vo.FileUploadVo;
import xyz.eazywu.music.object.vo.FileVo;
import xyz.eazywu.music.service.FileService;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    private final FileUploadMapper fileUploadMapper;

    private final FileMapper fileMapper;

    /**
     * 文件上传初始化
     */
    @PostMapping("/upload_init")
    FileUploadVo initUpload(@Validated @RequestBody FileUploadReq fileUploadReq) {
        return fileUploadMapper.toVo(fileService.initUpload(fileUploadReq));
    }

    /**
     * 文件上传完成
     */
    @PostMapping("/{id}/upload_finish")
    FileVo finishUpload(@PathVariable String id) {
        return fileMapper.toVo(fileService.finishUpload(id));
    }
}

