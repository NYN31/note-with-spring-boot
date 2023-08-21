package note.example.service.impl;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import note.example.dto.request.LoginRequest;
import note.example.dto.request.SignupRequest;
import note.example.dto.response.CommonResponse;
import note.example.exception.NotFoundException;
import note.example.model.User;
import note.example.repository.UserRepository;
import note.example.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.NotActiveException;
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
    public CommonResponse userSignUp(SignupRequest signupRequest) {
        validateUser(signupRequest);

        User user = User.builder()
                .name(signupRequest.name)
                .email(signupRequest.email)
                .password(signupRequest.password)
                .build();
        userRepository.save(user);
        log.info("Successfully Signup");

        return buildResponse(user, "Signup Successfully");
    }

    @Override
    public CommonResponse userLogin(LoginRequest loginRequest) {
        validateLoginUser(loginRequest);
        return null;
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

        if(userByEmail.isPresent()) {
           if(Objects.equals(userByEmail.get().password, loginRequest.password)) {
               return ;
           }
           else throw new RuntimeException("Password Does not match");
        }
        else {
            throw new NotFoundException("User not found");
        }
    }

    public CommonResponse buildResponse(Object object, String message) {
        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .status(message)
                .data(object)
                .build();
    }
}
