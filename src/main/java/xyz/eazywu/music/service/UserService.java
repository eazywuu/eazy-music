package xyz.eazywu.music.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import xyz.eazywu.music.object.dto.UserDto;
import xyz.eazywu.music.object.entity.User;
import xyz.eazywu.music.object.request.TokenCreateReq;
import xyz.eazywu.music.object.request.UserCreateReq;
import xyz.eazywu.music.object.request.UserUpdateReq;

public interface UserService extends UserDetailsService {

    UserDto create(UserCreateReq userCreateReq);

    UserDto get(String id);

    UserDto update(String id, UserUpdateReq userUpdateReq);

    void delete(String id);

    Page<UserDto> search(Pageable pageable);

    @Override
    User loadUserByUsername(String username);

    String createToken(TokenCreateReq tokenCreateReq);

    UserDto getCurrentUser();
}
