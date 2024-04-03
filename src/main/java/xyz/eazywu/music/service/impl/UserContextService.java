package xyz.eazywu.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import xyz.eazywu.music.exception.BizException;
import xyz.eazywu.music.exception.ExceptionType;
import xyz.eazywu.music.object.entity.User;
import xyz.eazywu.music.repository.UserRepository;

import java.util.Optional;

/**
 * 记录用户上下文服务
 */
public abstract class UserContextService {

    private UserRepository repository;

    protected User getCurrentUserEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return loadUserByUsername(authentication.getName());
    }


    protected User loadUserByUsername(String username) {
        Optional<User> user = repository.findByUsername(username);
        return user.orElseThrow(() -> new BizException(ExceptionType.USER_NOT_FOUND));
    }

    @Autowired
    private void setRepository(UserRepository repository) {
        this.repository = repository;
    }
}
