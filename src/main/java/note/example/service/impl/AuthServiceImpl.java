package note.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import note.example.dto.request.LoginRequest;
import note.example.dto.request.SignupRequest;
import note.example.dto.response.CommonResponse;
import note.example.dto.response.LoginResponse;
import note.example.dto.response.SignupResponse;
import note.example.exception.NotFoundException;
import note.example.model.User;
import note.example.repository.UserRepository;
import note.example.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SignupResponse userSignUp(SignupRequest signupRequest) {
        validateUser(signupRequest);

        User user = User.builder()
                .name(signupRequest.name)
                .email(signupRequest.email)
                .password(signupRequest.password)
                .build();

        userRepository.save(user);
        log.info("Signup Operation successful");
        return SignupResponse.form(user);
    }

    @Override
    public LoginResponse userLogin(LoginRequest loginRequest) {
        validateLoginUser(loginRequest);

        // TODO: generate a JWT token based on paramenter
        String token = "noyon@gmail.com"; // Test JWT

        return LoginResponse.form(token);
    }

    public void validateUser(SignupRequest signupRequest) {
        Optional<User> userByEmail = userRepository.findByEmail(signupRequest.email);

        if (userByEmail.isPresent()) {
            log.info("User email: {} is already exist.", signupRequest.email);
            throw new RuntimeException("User email is already exist");
        }
    }

    public void validateLoginUser(LoginRequest loginRequest) {
        Optional<User> userByEmail = userRepository.findByEmail(loginRequest.email);

        if (userByEmail.isPresent()) {
            if (!Objects.equals(userByEmail.get().password, loginRequest.password)) {
                throw new RuntimeException("Password does not match");
            }
        } else {
            throw new NotFoundException("User not found");
        }
    }
}
