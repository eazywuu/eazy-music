package com.wyz.music_springboot.dto;

import com.wyz.music_springboot.enums.Gender;
import com.wyz.music_springboot.vo.RoleVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String id;

    private String username;

    private String nickname;

    private List<RoleVo> roles;

    private Gender gender;

    private Boolean locked;

    private Boolean enabled;

    private String lastLoginIp;

    private Date lastLoginTime;
}
