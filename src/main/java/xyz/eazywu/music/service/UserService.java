package xyz.eazywu.music.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import xyz.eazywu.music.dto.TokenCreateRequest;
import xyz.eazywu.music.dto.UserCreateRequest;
import xyz.eazywu.music.dto.UserDto;
import xyz.eazywu.music.dto.UserUpdateRequest;
import xyz.eazywu.music.entity.User;

public interface UserService extends UserDetailsService {

    UserDto create(UserCreateRequest userCreateRequest);

    UserDto get(String id);

    UserDto update(String id, UserUpdateRequest userUpdateRequest);

    void delete(String id);

    Page<UserDto> search(Pageable pageable);

    User loadUserByUsername(String username);

    String createToken(TokenCreateRequest tokenCreateRequest);

    UserDto getCurrentUser();
}
