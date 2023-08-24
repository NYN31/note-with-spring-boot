package note.share.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import note.share.constant.enums.Role;
import note.share.model.User;
import note.share.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static note.share.constant.ConstantValue.AUTHORITY;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService {

    private final Algorithm algorithm;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiration.time}")
    private Long expirationTime;

    public JwtServiceImpl(@Value("${jwt.secret.key}") String secretKey) {
        this.algorithm = Algorithm.HMAC256(secretKey);
    }

    @Override
    public String createToken(User user) {
        log.info("Jwt token create request info: {}", user);
        try {
            List<Role> auth = Collections.singletonList(user.getRole());
            log.info("auth: {}", auth);
            String token = JWT.create()
                    .withSubject(user.getEmail())
                    .withJWTId(user.getId().toString())
                    .withClaim("userId", user.getId())
                    .withClaim("role", user.getRole().toString())
                    .withClaim(AUTHORITY, auth)
                    .withIssuer(issuer)
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plusSeconds(expirationTime))
                    .sign(algorithm);

            log.info("token info: {}", token);
            log.info("token created successfully.");
            return token;
        } catch (JWTCreationException exception) {
            log.info("Jwt creation exception {}", exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public DecodedJWT verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("role", Role.USER.getName())
                    .withClaimPresence("userId")
                    .withClaimPresence(AUTHORITY)
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);

            log.info("jwt token verification successful");
            return decodedJWT;
        } catch (JWTVerificationException exception) {
            log.info("jwt verification exception {}", exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }
}
