package note.share.controller;

import lombok.extern.slf4j.Slf4j;
import note.share.dto.request.GoogleLoginRequest;
import note.share.dto.request.LoginRequest;
import note.share.dto.request.RegistrationRequest;
import note.share.dto.response.CommonResponse;
import note.share.dto.response.LoginResponse;
import note.share.dto.response.RegistrationResponse;
import note.share.service.AuthService;
import note.share.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
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

    @PostMapping("/google/login")
    public ResponseEntity<LoginResponse> googleLogin(@RequestBody GoogleLoginRequest request) {
        log.info("Received request to login google user");
        return Util.form(authService.googleUserLogin(request), HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<RegistrationResponse> registration(@RequestBody RegistrationRequest registrationRequest) {
        log.info("Received request to signup user");
        return Util.form(authService.userRegistration(registrationRequest), HttpStatus.OK);
    }

    @PostMapping("/google/registration")
    public ResponseEntity<CommonResponse> googleRegistration(@RequestBody GoogleLoginRequest request) {
        log.info("Received request to signup google user");
        return Util.form(authService.googleUserRegistration(request), HttpStatus.OK);
    }
}
