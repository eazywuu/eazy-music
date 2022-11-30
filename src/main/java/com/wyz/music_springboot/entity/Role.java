package com.wyz.music_springboot.entity;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Role extends AbstractEntity{

    private String name;

    public String title;
}
