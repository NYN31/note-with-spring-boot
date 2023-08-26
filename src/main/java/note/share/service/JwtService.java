package note.share.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import note.share.model.User;

public interface JwtService {
    String createToken(User user);

    DecodedJWT verify(String token);
}
