package xyz.eazywu.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import xyz.eazywu.music.exception.BizException;
import xyz.eazywu.music.exception.ResultType;
import xyz.eazywu.music.object.entity.User;
import xyz.eazywu.music.repository.UserRepository;

import java.util.Optional;

public abstract class BaseService {

    private UserRepository userRepository;

    protected User getCurrentUserEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return loadUserByUsername(authentication.getName());
    }


    protected User loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new BizException(ResultType.USER_NOT_FOUND);
        }
        return user.get();
    }

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
