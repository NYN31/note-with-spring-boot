package note.example.service.impl;

import com.sun.jdi.InternalException;
import lombok.extern.slf4j.Slf4j;
import note.example.dto.request.LoginDto;
import note.example.dto.request.SignupDto;
import note.example.dto.response.CommonResponseDto;
import note.example.model.User;
import note.example.repository.UserRepository;
import note.example.service.AuthService;
import org.springframework.http.HttpStatus;
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
    public CommonResponseDto userSignUp(SignupDto signupDto) throws Exception {
        /*
        *
        *
        * */

        validateUser(signupDto);
        User user = new User();

        user.setName(signupDto.name);
        user.setEmail(signupDto.email);
        user.setPassword(signupDto.password);

        userRepository.save(user);

        CommonResponseDto commonResponseDto = new CommonResponseDto();
        commonResponseDto.setCode(200);
        commonResponseDto.setStatus("Signup success");
        commonResponseDto.setData(user);

        log.info("Successfully Signup");
        return commonResponseDto;
    }

    @Override
    public CommonResponseDto userLogin(LoginDto loginDto) {
        CommonResponseDto commonResponseDto = new CommonResponseDto();
        commonResponseDto.setCode(200);
        commonResponseDto.setStatus("login success");

        log.info("Successfully Login");
        return commonResponseDto;
    }

    public void validateUser(SignupDto signupDto) throws Exception {
        log.info("User validation");
        Optional<User> userByEmail = userRepository.findByEmail(signupDto.email);

        if(userByEmail.isPresent()) {
            log.info("User email: {} is already exist.", signupDto.email);
            throw new RuntimeException("User email is already exist");
        }
    }
}
