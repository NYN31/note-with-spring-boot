package note.example.service;

import note.example.dto.request.LoginDto;
import note.example.dto.request.SignupDto;
import note.example.dto.response.CommonResponseDto;

public interface AuthService {
    CommonResponseDto userSignUp(SignupDto signupDto) throws Exception;

    CommonResponseDto userLogin(LoginDto loginDto);
}
