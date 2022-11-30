package com.wyz.music_springboot.dto;

import com.wyz.music_springboot.enums.Gender;
import com.wyz.music_springboot.vo.RoleVo;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String id;

    private String username;

    private String nickname;

    private List<RoleVo> roles;

    private Gender gender;

}
