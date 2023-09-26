package note.share.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.extern.slf4j.Slf4j;
import note.share.dto.response.GoogleLoginUserResponse;
import note.share.service.GoogleTokenVerifierService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Slf4j
@Service
public class GoogleTokenVerifierServiceImpl implements GoogleTokenVerifierService {
    @Value("${google.credential.clientId}")
    private String clientId;

    @Override
    public GoogleLoginUserResponse googleTokenVerify(String token) {
        log.info("google token verification {}.", token);
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                .Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singleton(clientId))
                .build();

        try {
            GoogleIdToken idToken = verifier.verify(token);
            if (idToken == null) {
                throw new RuntimeException("Invalid id token");
            }
            Payload payload = idToken.getPayload();
            log.info("google token verification successful.");
            return GoogleLoginUserResponse.form(payload);
        } catch (GeneralSecurityException | IOException exception) {
            log.warn("google token verification failed {}.", token);
            throw new RuntimeException(exception.getMessage());
        }
    }
}
