package note.share.utility;

import note.share.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class Util {
    public Util() {
    }

    public static <T> ResponseEntity<T> form(HttpStatus status) {
        return form(null, status);
    }

    public static <T> ResponseEntity<T> form(T body, HttpStatus status) {
        return new ResponseEntity<>(body, status);
    }

    public static User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    public static String getUuid() {
        return UUID.randomUUID().toString();
    }
}
