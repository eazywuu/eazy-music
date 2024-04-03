package xyz.eazywu.music.object.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import xyz.eazywu.music.object.enums.GenderType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails {

    /**
     * 用户名
     */
    @Column(unique = true)
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户性别
     */
    @Enumerated(EnumType.STRING)
    private GenderType gender;

    /**
     * 角色：普通用户，管理员
     */
    @ManyToMany(cascade = {}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    /**
     * 账户是否被锁定
     */
    private Boolean locked = false;
    /**
     * 账户是否通行
     */
    private Boolean enabled = true;
    /**
     * 上次登录ip
     */
    private String lastLoginIp;
    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach((role) -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }

    /**
     * 当前用户没有过期？
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 当前用户没有锁定？
     */
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    /**
     * 证书没有过期？
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 当前用户可通行？
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
