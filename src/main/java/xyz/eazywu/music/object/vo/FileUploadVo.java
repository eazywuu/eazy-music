package xyz.eazywu.music.object.vo;

import lombok.Data;

@Data
public class FileUploadVo {

    private String fileId;

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


//    private String bucket;

//    private String region;

    private Long startTime;

    private Long expiredTime;
}
