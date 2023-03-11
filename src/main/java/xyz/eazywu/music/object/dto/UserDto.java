package xyz.eazywu.music.object.dto;

import xyz.eazywu.music.enums.GenderEnum;
import xyz.eazywu.music.object.vo.RoleVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String id;

    private String username;

    private String nickname;

    private List<RoleVo> roles;

    private GenderEnum genderEnum;

    private Boolean locked;

    private Boolean enabled;

    private String lastLoginIp;

    private Date lastLoginTime;
}
