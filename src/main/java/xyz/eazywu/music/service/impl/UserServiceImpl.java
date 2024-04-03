package xyz.eazywu.music.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.eazywu.music.exception.BizException;
import xyz.eazywu.music.exception.ExceptionType;
import xyz.eazywu.music.filter.JwtProvider;
import xyz.eazywu.music.mapper.UserMapper;
import xyz.eazywu.music.object.dto.UserDto;
import xyz.eazywu.music.object.entity.User;
import xyz.eazywu.music.object.request.TokenCreateReq;
import xyz.eazywu.music.object.request.UserCreateReq;
import xyz.eazywu.music.object.request.UserUpdateReq;
import xyz.eazywu.music.repository.UserRepository;
import xyz.eazywu.music.service.UserService;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl extends UserContextService implements UserService {

    private final UserMapper mapper;
    private final UserRepository repository;
    // 密码加密器
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public UserDto create(UserCreateReq userCreateReq) {
        checkUsername(userCreateReq.getUsername());
        User user = mapper.createEntity(userCreateReq);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.toDto(repository.save(user));
    }

    @Override
    public UserDto get(String id) {
        return mapper.toDto(checkUserExist(id));
    }

    @Override
    public UserDto update(String id, UserUpdateReq userUpdateReq) {
        User user = checkUserExist(id);
        userUpdateReq.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.toDto(repository.save(mapper.updateEntity(user, userUpdateReq)));
    }

    @Override
    public void delete(String id) {
        repository.delete(checkUserExist(id));
    }

    @Override
    public Page<UserDto> search(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public String createToken(TokenCreateReq tokenCreateReq) {
        User user = loadUserByUsername(tokenCreateReq.getUsername());

        if (!passwordEncoder.matches(tokenCreateReq.getPassword(), user.getPassword())) {
            throw new BizException(ExceptionType.USER_PASSWORD_NOT_MATCH);
        }
        if (!user.isEnabled()) {
            throw new BizException(ExceptionType.USER_NOT_ENABLED);
        }
        if (!user.isAccountNonLocked()) {
            throw new BizException(ExceptionType.USER_LOCKED);
        }

        return jwtProvider.generateToken(user.getUsername());
    }

    /**
     * 通过用户名加载用户，生成token时使用，用户名从请求或者上下文获取
     */
    @Override
    public User loadUserByUsername(String username) {
        return super.loadUserByUsername(username);
    }

    /**
     * 通过上下文获取用户名
     */
    @Override
    public UserDto getCurrentUser() {
        return mapper.toDto(super.getCurrentUserEntity());
    }

    private void checkUsername(String username) {
        Optional<User> user = repository.findByUsername(username);

        user.orElseThrow(() -> new BizException(ExceptionType.USER_NAME_DUPLICATE));
    }

    private User checkUserExist(String id) {
        Optional<User> user = repository.findById(id);

        return user.orElseThrow(() -> new BizException(ExceptionType.USER_NOT_FOUND));

    }
}
