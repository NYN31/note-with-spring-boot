package note.share.controller;

import lombok.extern.slf4j.Slf4j;
import note.share.dto.request.LoginRequest;
import note.share.dto.request.SignupRequest;
import note.share.dto.response.LoginResponse;
import note.share.dto.response.SignupResponse;
import note.share.service.AuthService;
import note.share.utility.Util;
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
