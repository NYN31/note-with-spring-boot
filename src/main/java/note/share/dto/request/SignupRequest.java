package note.share.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class SignupRequest {
    @Length(min=3, max=40)
    @NotNull
    public String name;

    @NotNull
    public String email;

    @Length(min=8, max=40)
    @NotNull
    public String password;
}
