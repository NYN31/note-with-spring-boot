package note.share.service.impl;

import lombok.extern.slf4j.Slf4j;
import note.share.constant.enums.RegistrationType;
import note.share.constant.enums.Role;
import note.share.constant.enums.Status;
import note.share.dto.request.GoogleLoginRequest;
import note.share.dto.request.LoginRequest;
import note.share.dto.request.RegistrationRequest;
import note.share.dto.response.CommonResponse;
import note.share.dto.response.GoogleLoginUserResponse;
import note.share.dto.response.LoginResponse;
import note.share.dto.response.RegistrationResponse;
import note.share.model.User;
import note.share.repository.UserRepository;
import note.share.service.AuthService;
import note.share.service.GoogleTokenVerifierService;
import note.share.service.JwtService;
import note.share.service.UserService;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    private final GoogleTokenVerifierService googleTokenVerifierService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthServiceImpl(
            GoogleTokenVerifierService googleTokenVerifierService,
            JwtService jwtService,
            UserRepository userRepository,
            UserService userService,
            PasswordEncoder bCryptPasswordEncoder
    ) {
        this.googleTokenVerifierService = googleTokenVerifierService;
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
    public LoginResponse googleUserLogin(GoogleLoginRequest request) {
        log.info("login by google: {}", request);
        GoogleLoginUserResponse googleResponse = googleTokenVerifierService
                .googleTokenVerify(request.getToken());
        User user = userService.findUserByEmail(googleResponse.getEmail());
        String token = jwtService.createToken(user);
        return LoginResponse.form(user.getName(), user.getUsername(), user.getEmail(), token);
    }

    @Override
    public RegistrationResponse userRegistration(RegistrationRequest registrationRequest) {
        log.info("Registration request");
        validateRegistrationUser(registrationRequest.getEmail(), registrationRequest.getUsername());
        User user = createUser(registrationRequest, RegistrationType.BASIC, Role.USER);
        log.info("User info {}", user);
        userRepository.save(user);
        return RegistrationResponse.form(user);
    }

    @Override
    public CommonResponse googleUserRegistration(GoogleLoginRequest request) {
        log.info("Registration request by google: {}", request);
        GoogleLoginUserResponse googleResponse = googleTokenVerifierService
                .googleTokenVerify(request.getToken());
        String username = emailToUserName(googleResponse.getEmail());
        validateRegistrationUser(googleResponse.getEmail(), username);
        RegistrationRequest userCreateRequest = new RegistrationRequest();
        userCreateRequest.setName(googleResponse.getName());
        userCreateRequest.setUsername(username);
        userCreateRequest.setEmail(googleResponse.getEmail());

        User user = createUser(userCreateRequest, RegistrationType.GOOGLE, Role.USER);
        log.info("user info {}", user);
        userRepository.save(user);
        return CommonResponse.form(HttpStatus.CREATED.value(), "Registration Successful.");
    }

    public void validateRegistrationUser(String email, String username) {
        log.info("Validating user registration by email: {} and username: {}", email, username);
        userService.throwIfUserExistByEmail(email);
        userService.throwIfUserExistByUsername(username);
    }

    public User createUser(
            RegistrationRequest registrationRequest,
            RegistrationType registrationType,
            Role roleType) {
        log.info("Create user {}", registrationRequest);
        return User.builder()
                .name(registrationRequest.getName())
                .username(registrationRequest.getUsername())
                .email(registrationRequest.getEmail())
                .password(Objects.equals(registrationType.getName(), "GOOGLE") ?
                        null : bCryptPasswordEncoder.encode(registrationRequest.getPassword()))
                .profilePicture(null)
                .registrationType(registrationType)
                .role(roleType)
                .userStatus(Status.ACTIVE)
                .build();
    }

    private String emailToUserName(String email) {
        int len = email.length();
        StringBuilder userName = new StringBuilder();

        for (int i = 0; i < len; i++) {
            if (email.charAt(i) == '@') {
                break;
            } else {
                userName.append(email.charAt(i));
            }
        }
        return userName.toString();
    }
}
