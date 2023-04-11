package xyz.eazywu.music.object.dto;

import lombok.Data;

/**
 * 文件上传dto，后台生成临时token给前台，前台通过临时token上传到cos服务器
 */
@Data
public class FileUploadDto {
    /**
     * FileEntity的id
     */
    private String fileId;
    /**
     * FileEntity的hash-key
     */
    private String fileKey;
    /**
     * 这里的secretId和secretKey代表了用于申请临时密钥的永久身份
     * 云api密钥id
     */
    private String secretId;
    /**
     * 云api密钥key
     */
    private String secretKey;

    /**
     * 会话令牌
     */
    private String sessionToken;

    private Long startTime;

    private Long expiredTime;
}
