package note.share.service;

import note.share.dto.response.GoogleLoginUserResponse;

public interface GoogleTokenVerifierService {
    GoogleLoginUserResponse googleTokenVerify(String token);
}
