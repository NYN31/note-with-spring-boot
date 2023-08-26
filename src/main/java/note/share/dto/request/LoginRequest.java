package note.share.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Builder
@Getter
@Setter
@ToString
public class LoginRequest {
    @NotNull
    private String email;

    @Length(min=8, max=40)
    @NotNull
    private String password;
}
