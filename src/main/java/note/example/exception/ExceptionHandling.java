package note.example.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import note.example.dto.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex) {
        return new ResponseEntity<>(
                buildResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(Exception ex) {
        return new ResponseEntity<>(
                buildResponse(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    public CommonResponse buildResponse(Integer code, String message) {
        return CommonResponse.builder()
                .code(code)
                .status(message)
                .build();
    }
}