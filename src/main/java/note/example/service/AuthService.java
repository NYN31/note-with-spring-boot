package note.example.service;

import note.example.dto.request.LoginRequest;
import note.example.dto.request.SignupRequest;
import note.example.dto.response.CommonResponse;
import note.example.dto.response.LoginResponse;
import note.example.dto.response.SignupResponse;

public interface AuthService {
    SignupResponse userSignUp(SignupRequest signupRequest) throws Exception;

    LoginResponse userLogin(LoginRequest loginRequest);
}
