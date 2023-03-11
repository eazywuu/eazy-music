package xyz.eazywu.music.object.vo;

import lombok.Data;
import xyz.eazywu.music.enums.GenderEnum;

import java.util.List;

@Data
public class UserVo {
    private String id;

    private String username;

    private String nickname;

    private List<RoleVo> roles;

    private GenderEnum genderEnum;

    private Boolean locked;

    private Boolean enabled;
}
