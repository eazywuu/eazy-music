package xyz.eazywu.music.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.eazywu.music.config.SecurityConfig;
import xyz.eazywu.music.exception.BizException;
import xyz.eazywu.music.exception.ResultType;
import xyz.eazywu.music.mapper.UserMapper;
import xyz.eazywu.music.object.dto.UserDto;
import xyz.eazywu.music.object.entity.User;
import xyz.eazywu.music.object.request.TokenCreateReq;
import xyz.eazywu.music.object.request.UserCreateReq;
import xyz.eazywu.music.object.request.UserUpdateReq;
import xyz.eazywu.music.repository.UserRepository;
import xyz.eazywu.music.service.UserService;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl extends UserContextService implements UserService {

    private final UserMapper userMapper;
    private final UserRepository repository;
    // 密码加密器
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto create(UserCreateReq userCreateReq) {
        checkUsername(userCreateReq.getUsername());
        User user = userMapper.createEntity(userCreateReq);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDto(repository.save(user));
    }


    @Override
    public UserDto get(String id) {
        return userMapper.toDto(checkUserExist(id));
    }

    @Override
    public UserDto update(String id, UserUpdateReq userUpdateReq) {
        User user = checkUserExist(id);
        userUpdateReq.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDto(repository.save(userMapper.updateEntity(user, userUpdateReq)));
    }

    @Override
    public void delete(String id) {
        repository.delete(checkUserExist(id));
    }

    @Override
    public Page<UserDto> search(Pageable pageable) {
        return repository.findAll(pageable).map(userMapper::toDto);
    }

    /**
     * 通过用户名加载用户，生成token时使用，用户名从请求或者上下文获取
     */
    @Override
    public User loadUserByUsername(String username) {
        return super.loadUserByUsername(username);
    }

    @Override
    public String createToken(TokenCreateReq tokenCreateReq) {
        User user = loadUserByUsername(tokenCreateReq.getUsername());
        if (!passwordEncoder.matches(tokenCreateReq.getPassword(), user.getPassword())) {
            throw new BizException(ResultType.USER_PASSWORD_NOT_MATCH);
        }
        if (!user.isEnabled()) {
            throw new BizException(ResultType.USER_NOT_ENABLED);
        }
        if (!user.isAccountNonLocked()) {
            throw new BizException(ResultType.USER_LOCKED);
        }
        return JWT.create()
                // 主题：用户名
                .withSubject(user.getUsername())
                // 过期时间：当前时间 + 有效时间
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION_TIME))
                // 签名：使用签名算法对密钥加密
                .sign(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()));
    }

    /**
     * 通过上下文获取用户名，
     */
    @Override
    public UserDto getCurrentUser() {
        return userMapper.toDto(super.getCurrentUserEntity());
    }

    private void checkUsername(String username) {
        Optional<User> user = repository.findByUsername(username);
        if (user.isPresent()) {
            throw new BizException(ResultType.USER_NAME_DUPLICATE);
        }
    }

    private User checkUserExist(String id) {
        Optional<User> user = repository.findById(id);
        if (!user.isPresent()) {
            throw new BizException(ResultType.USER_NOT_FOUND);
        }
        return user.get();
    }
}
