package xyz.eazywu.music.object.vo;

import lombok.Data;
import xyz.eazywu.music.object.enums.GenderType;

import java.util.List;

@Data
public class UserVo extends BaseVo {

    private String username;

    private String nickname;

    private List<RoleVo> roles;

    private GenderType gender;

    private Boolean locked;

    private Boolean enabled;
}
