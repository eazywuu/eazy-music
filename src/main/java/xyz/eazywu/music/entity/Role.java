package xyz.eazywu.music.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * 角色实体类
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AbstractEntity {

    /**
     * 角色名： ROLE_USER, ROLE_ADMIN
     */
    private String name;

    /**
     * 角色: 普通用户，超级管理员
     */
    private String title;
}
