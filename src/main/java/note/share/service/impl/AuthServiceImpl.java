package note.share.service.impl;

import lombok.extern.slf4j.Slf4j;
import note.share.constant.enums.RegistrationType;
import note.share.constant.enums.Role;
import note.share.constant.enums.Status;
import note.share.dto.request.LoginRequest;
import note.share.dto.request.RegistrationRequest;
import note.share.dto.response.LoginResponse;
import note.share.dto.response.RegistrationResponse;
import note.share.model.User;
import note.share.repository.UserRepository;
import note.share.service.AuthService;
import note.share.service.JwtService;
import note.share.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthServiceImpl(
            JwtService jwtService,
            UserRepository userRepository,
            UserService userService,
            PasswordEncoder bCryptPasswordEncoder
    ) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public LoginResponse userLogin(LoginRequest loginRequest) {
        User user = userService.findUserByEmail(loginRequest.getEmail());
        if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            log.info("Wrong email: {} or password: {}", loginRequest.getEmail(), loginRequest.getPassword());
            throw new RuntimeException("Wrong email or password.");
        }

        String token = jwtService.createToken(user);
        return LoginResponse.form(user.getName(), user.getUsername(), user.getEmail(), token);
    }

    @Override
    public RegistrationResponse userRegistration(RegistrationRequest registrationRequest) {
        log.info("Registration request");
        validateRegistrationUser(registrationRequest.getEmail(), registrationRequest.getUsername());
        User user = createUser(registrationRequest);
        log.info("User info {}", user);
        userRepository.save(user);
        return RegistrationResponse.form(user);
    }

    public void validateRegistrationUser(String email, String username) {
        log.info("Validating user registration by email: {} and username: {}", email, username);
        userService.throwIfUserExistByEmail(email);
        userService.throwIfUserExistByUsername(username);
    }

    public User createUser(RegistrationRequest registrationRequest) {
        log.info("Create user {}", registrationRequest);
        return User.builder()
                .name(registrationRequest.getName())
                .username(registrationRequest.getUsername())
                .email(registrationRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(registrationRequest.getPassword()))
                .profilePicture(null)
                .registrationType(RegistrationType.BASIC)
                .role(Role.USER)
                .userStatus(Status.ACTIVE)
                .build();
    }
}
