package note.example.service;

import note.example.dto.request.LoginRequest;
import note.example.dto.request.SignupRequest;
import note.example.dto.response.CommonResponse;

public interface AuthService {
    CommonResponse userSignUp(SignupRequest signupRequest) throws Exception;

    CommonResponse userLogin(LoginRequest loginRequest);
}
