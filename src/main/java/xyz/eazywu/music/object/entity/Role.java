package xyz.eazywu.music.object.entity;

import lombok.*;

import javax.persistence.Entity;

/**
 * 角色实体类
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity {

    /**
     * 角色名： ROLE_USER, ROLE_ADMIN
     */
    private String name;

    /**
     * 角色: 普通用户，超级管理员
     */
    private String title;
}
