package note.share.service.impl;

import lombok.extern.slf4j.Slf4j;
import note.share.model.User;
import note.share.repository.UserRepository;
import note.share.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void throwIfUserExistByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            log.warn("User already exist by {}", email);
            throw new RuntimeException("User Already Exist.");
        }
    }

    @Override
    public void throwIfUserExistByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            log.warn("Username already exist by {}", username);
            throw new RuntimeException("Username already exist.");
        }
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("Unauthorized User.");
        }
        return user.get();
    }

    @Override
    public User findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new RuntimeException("Unauthorized User.");
        }
        return user.get();
    }
}
