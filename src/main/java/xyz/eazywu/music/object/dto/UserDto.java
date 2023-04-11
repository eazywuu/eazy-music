package xyz.eazywu.music.object.dto;

import xyz.eazywu.music.object.enums.GenderType;
import xyz.eazywu.music.object.vo.RoleVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto extends BaseDto {

    private String username;

    private String nickname;

    private List<RoleVo> roles;

    private GenderType gender;

    private Boolean locked;

    private Boolean enabled;

    private String lastLoginIp;

    private Date lastLoginTime;
}
