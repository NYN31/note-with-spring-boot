package note.share.service;

import note.share.dto.request.LoginRequest;
import note.share.dto.request.RegistrationRequest;
import note.share.dto.response.LoginResponse;
import note.share.dto.response.RegistrationResponse;

public interface AuthService {
    LoginResponse userLogin(LoginRequest loginRequest);

    RegistrationResponse userRegistration(RegistrationRequest registrationRequest);
}
