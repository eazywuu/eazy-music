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
import xyz.eazywu.music.object.request.TokenCreateRequestDto;
import xyz.eazywu.music.object.request.UserCreateRequestDto;
import xyz.eazywu.music.object.dto.UserDto;
import xyz.eazywu.music.object.request.UserUpdateRequestDto;
import xyz.eazywu.music.object.entity.UserEntity;
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
        UserEntity userEntity = userMapper.createEntity(userCreateRequestDto);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userMapper.toDto(userRepository.save(userEntity));
    }


    @Override
    public UserDto get(String id) {
        UserEntity userEntity = checkUserExist(id);
        return userMapper.toDto(userEntity);
    }

    @Override
    public UserDto update(String id, UserUpdateRequestDto userUpdateRequestDto) {
        UserEntity userEntity = checkUserExist(id);
        userUpdateRequestDto.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userMapper.toDto(userRepository.save(userMapper.updateEntity(userEntity, userUpdateRequestDto)));
    }

    @Override
    public void delete(String id) {
        UserEntity userEntity = checkUserExist(id);
        userRepository.delete(userEntity);
    }

    @Override
    public Page<UserDto> search(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDto);
    }

    @Override
    public UserEntity loadUserByUsername(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return user.get();
    }

    @Override
    public String createToken(TokenCreateRequestDto tokenCreateRequestDto) {
        UserEntity userEntity = loadUserByUsername(tokenCreateRequestDto.getUsername());
        // TODO LOGIN ERROR!
        if (passwordEncoder.matches(passwordEncoder.encode(tokenCreateRequestDto.getPassword()), userEntity.getPassword())) {
            throw new BizException(ExceptionType.USER_PASSWORD_NOT_MATCH);
        }
        if (!userEntity.isEnabled()) {
            throw new BizException(ExceptionType.USER_NOT_ENABLED);
        }
        if (!userEntity.isAccountNonLocked()) {
            throw new BizException(ExceptionType.USER_LOCKED);
        }
        return JWT.create()
                // 主题：用户名
                .withSubject(userEntity.getUsername())
                // 过期时间：当前时间 + 有效时间
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION_TIME))
                // 签名：使用签名算法对密钥加密
                .sign(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()));
    }

    @Override
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = loadUserByUsername(authentication.getName());
        return userMapper.toDto(userEntity);
    }

    private void checkUsername(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new BizException(ExceptionType.USER_NAME_DUPLICATE);
        }
    }

    private UserEntity checkUserExist(String id) {
        Optional<UserEntity> user = userRepository.findById(id);
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
