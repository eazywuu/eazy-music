package com.wyz.music_springboot.vo;

import com.wyz.music_springboot.enums.Gender;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Data
public class UserVo {
    private String id;

    private String username;

    private String nickname;

    private List<RoleVo> roles;

    private Gender gender;
}
