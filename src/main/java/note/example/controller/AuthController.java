package note.example.controller;

import lombok.extern.slf4j.Slf4j;
import note.example.dto.request.LoginRequest;
import note.example.dto.request.SignupRequest;
import note.example.dto.response.CommonResponse;
import note.example.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/sign-up")
    public ResponseEntity<CommonResponse> signup(@RequestBody SignupRequest signupRequest) throws Exception {
        log.info("In user sign up endpoint");
        return new ResponseEntity<>(authService.userSignUp(signupRequest), HttpStatus.OK);
    }

    @PostMapping("/login")
    public CommonResponse login(@RequestBody LoginRequest loginRequest) {
        log.info("In user login endpoint");
        return authService.userLogin(loginRequest);
    }
}
