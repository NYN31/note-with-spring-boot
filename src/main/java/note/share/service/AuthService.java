package note.share.service;

import note.share.dto.request.GoogleLoginRequest;
import note.share.dto.request.LoginRequest;
import note.share.dto.request.RegistrationRequest;
import note.share.dto.response.CommonResponse;
import note.share.dto.response.LoginResponse;
import note.share.dto.response.RegistrationResponse;

public interface AuthService {
    LoginResponse userLogin(LoginRequest loginRequest);
    LoginResponse googleUserLogin(GoogleLoginRequest request);
    RegistrationResponse userRegistration(RegistrationRequest registrationRequest);
    CommonResponse googleUserRegistration(GoogleLoginRequest request);
}
