package note.example.dto.request;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class LoginRequest {
    @NotNull
    public String email;

    @Length(min=8, max=40)
    @NotNull
    public String password;
}
