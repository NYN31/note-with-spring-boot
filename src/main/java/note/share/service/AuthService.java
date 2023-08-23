package note.share.service;

import note.share.dto.request.LoginRequest;
import note.share.dto.request.SignupRequest;
import note.share.dto.response.LoginResponse;
import note.share.dto.response.SignupResponse;

public interface AuthService {
    SignupResponse userSignUp(SignupRequest signupRequest);

    LoginResponse userLogin(LoginRequest loginRequest);
}
