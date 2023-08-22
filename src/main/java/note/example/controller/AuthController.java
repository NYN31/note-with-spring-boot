package note.example.controller;

import lombok.extern.slf4j.Slf4j;
import note.example.dto.request.LoginRequest;
import note.example.dto.request.SignupRequest;
import note.example.dto.response.CommonResponse;
import note.example.dto.response.LoginResponse;
import note.example.dto.response.SignupResponse;
import note.example.service.AuthService;
import note.example.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        log.info("Received request to login user");
        return Util.form(authService.userLogin(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest signupRequest) {
        log.info("Received request to signup user");
        return Util.form(authService.userSignUp(signupRequest), HttpStatus.OK);
    }
}
