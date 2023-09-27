package note.share.configuration;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.Nullable;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import note.share.model.User;
import note.share.repository.UserRepository;
import note.share.service.JwtService;
import note.share.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static note.share.constant.ConstantValue.*;


@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
            @Nullable HttpServletRequest request,
            @Nullable HttpServletResponse response,
            @Nullable FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        if (authorization == null || !authorization.startsWith(AUTHORIZATION_SCHEME)) {
            assert filterChain != null;
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.substring(AUTHORIZATION_SCHEME.length() + 1);
        SecurityContext contextHolder = SecurityContextHolder.getContext();

        try {
            DecodedJWT decodedJWT = jwtService.verify(token);
            Long userId = decodedJWT.getClaim("userId").asLong();
            User user = findUserById(userId);
            setAuthenticationContext(decodedJWT, user);
        } catch (Exception e) {
            log.info("token verification failed.");
            contextHolder.setAuthentication(null);
        }
    }

    private void setAuthenticationContext(DecodedJWT decodedJWT, User user) {
        List<String> authorities = decodedJWT.getClaim(AUTHORITY).asList(String.class);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user,
                decodedJWT.getSubject(),
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
        );

        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    private User findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("Unauthorized User.");
        }
        return user.get();
    }
}
