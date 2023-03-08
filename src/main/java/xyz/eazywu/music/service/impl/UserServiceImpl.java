package xyz.eazywu.music.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.eazywu.music.config.SecurityConfig;
import xyz.eazywu.music.dto.TokenCreateRequestDto;
import xyz.eazywu.music.dto.UserCreateRequestDto;
import xyz.eazywu.music.dto.UserDto;
import xyz.eazywu.music.dto.UserUpdateRequestDto;
import xyz.eazywu.music.entity.User;
import xyz.eazywu.music.exception.BizException;
import xyz.eazywu.music.exception.ExceptionType;
import xyz.eazywu.music.mapper.UserMapper;
import xyz.eazywu.music.repository.UserRepository;
import xyz.eazywu.music.service.UserService;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserMapper userMapper;
    // 密码加密器
    PasswordEncoder passwordEncoder;

    @Override
    public UserDto create(UserCreateRequestDto userCreateRequestDto) {
        checkUsername(userCreateRequestDto.getUsername());
        User user = userMapper.createEntity(userCreateRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }


    @Override
    public UserDto get(String id) {
        User user = checkUserExist(id);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto update(String id, UserUpdateRequestDto userUpdateRequestDto) {
        User user = checkUserExist(id);
        userUpdateRequestDto.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDto(userRepository.save(userMapper.updateEntity(user, userUpdateRequestDto)));
    }

    @Override
    public void delete(String id) {
        User user = checkUserExist(id);
        userRepository.delete(user);
    }

    @Override
    public Page<UserDto> search(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDto);
    }

    @Override
    public User loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return user.get();
    }

    @Override
    public String createToken(TokenCreateRequestDto tokenCreateRequestDto) {
        User user = loadUserByUsername(tokenCreateRequestDto.getUsername());
        // TODO LOGIN ERROR!
        if (passwordEncoder.matches(passwordEncoder.encode(tokenCreateRequestDto.getPassword()), user.getPassword())) {
            throw new BizException(ExceptionType.USER_PASSWORD_NOT_MATCH);
        }
        if (!user.isEnabled()) {
            throw new BizException(ExceptionType.USER_NOT_ENABLED);
        }
        if (!user.isAccountNonLocked()) {
            throw new BizException(ExceptionType.USER_LOCKED);
        }
        return JWT.create()
                // 主题：用户名
                .withSubject(user.getUsername())
                // 过期时间：当前时间 + 有效时间
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION_TIME))
                // 签名：使用签名算法对密钥加密
                .sign(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()));
    }

    @Override
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = loadUserByUsername(authentication.getName());
        return userMapper.toDto(user);
    }

    private void checkUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new BizException(ExceptionType.USER_NAME_DUPLICATE);
        }
    }

    private User checkUserExist(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return user.get();
    }

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    private void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


}
