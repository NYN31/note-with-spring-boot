package note.share.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import note.share.dto.Validator;
import org.hibernate.validator.constraints.Length;

@Builder
@Getter
@Setter
@ToString
public class LoginRequest implements Validator {
    @NotNull
    private String email;

    @Length(min=8, max=40)
    @NotNull
    private String password;

    @Override
    public void RequestValidator() {
        if(email == null || password == null){
            throw new RuntimeException("Required field should not be null!");
        }
    }
}
