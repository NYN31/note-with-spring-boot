package note.share.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Util {
    public Util() {
    }

    public static <T> ResponseEntity<T> form(HttpStatus status) {
        return form(null, status);
    }

    public static <T> ResponseEntity<T> form(T body, HttpStatus status) {
        return new ResponseEntity<>(body, status);
    }
}
