package xyz.eazywu.music.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import xyz.eazywu.music.dto.TokenCreateRequestDto;
import xyz.eazywu.music.dto.UserCreateRequestDto;
import xyz.eazywu.music.dto.UserDto;
import xyz.eazywu.music.dto.UserUpdateRequestDto;
import xyz.eazywu.music.entity.User;

public interface UserService extends UserDetailsService {

    UserDto create(UserCreateRequestDto userCreateRequestDto);

    UserDto get(String id);

    UserDto update(String id, UserUpdateRequestDto userUpdateRequestDto);

    void delete(String id);

    Page<UserDto> search(Pageable pageable);

    User loadUserByUsername(String username);

    String createToken(TokenCreateRequestDto tokenCreateRequestDto);

    UserDto getCurrentUser();
}
