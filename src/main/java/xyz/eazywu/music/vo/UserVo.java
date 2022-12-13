package xyz.eazywu.music.vo;

import lombok.Data;
import xyz.eazywu.music.enums.Gender;

import java.util.List;

@Data
public class UserVo {
    private String id;

    private String username;

    private String nickname;

    private List<RoleVo> roles;

    private Gender gender;

    private Boolean locked;

    private Boolean enabled;
}
