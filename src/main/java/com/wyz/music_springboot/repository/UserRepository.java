package com.wyz.music_springboot.repository;

import com.wyz.music_springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User getByUsername(String username);
}