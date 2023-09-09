package note.share.service.impl;

import lombok.extern.slf4j.Slf4j;
import note.share.dto.request.PasswordUpdateRequest;
import note.share.dto.response.PasswordUpdateResponse;
import note.share.model.User;
import note.share.repository.UserRepository;
import note.share.service.UserService;
import note.share.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public PasswordUpdateResponse passwordUpdate(PasswordUpdateRequest request) {
        log.info("Update password request");
        User user = Util.getUser();
        validatePassword(user, request);

        user.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        log.info("Password updated successfully");
        return PasswordUpdateResponse.form();
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

    private void validatePassword(User user, PasswordUpdateRequest request) {
        log.info("Validate current and new password");
        if (request.getCurrentPassword() == null) {
            throw new RuntimeException("Current Password should not be null.");
        }
        if (!bCryptPasswordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Password does not matched");
        }
        if (!request.getCurrentPassword().equals(request.getNewPassword())) {
            throw new RuntimeException("Password does not matched.");
        }
        if (!user.getPassword().equals(request.getCurrentPassword())) {
            throw new RuntimeException("Old password is incorrect.");
        }
    }
}
