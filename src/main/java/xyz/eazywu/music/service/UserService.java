package xyz.eazywu.music.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import xyz.eazywu.music.object.request.TokenCreateRequestDto;
import xyz.eazywu.music.object.request.UserCreateRequestDto;
import xyz.eazywu.music.object.dto.UserDto;
import xyz.eazywu.music.object.request.UserUpdateRequestDto;
import xyz.eazywu.music.object.entity.UserEntity;

public interface UserService extends UserDetailsService {

    UserDto create(UserCreateRequestDto userCreateRequestDto);

    UserDto get(String id);

    UserDto update(String id, UserUpdateRequestDto userUpdateRequestDto);

    void delete(String id);

    Page<UserDto> search(Pageable pageable);

    UserEntity loadUserByUsername(String username);

    String createToken(TokenCreateRequestDto tokenCreateRequestDto);

    UserDto getCurrentUser();
}
