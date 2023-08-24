package note.share.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import note.share.constant.enums.RegistrationType;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
public class RegistrationRequest {
    @Length(min=3, max=40)
    @NotNull
    private String name;

    @Length(min=3, max=40)
    @NotNull
    private String username;

    @NotNull
    private String email;

    @Length(min=8, max=40)
    @NotNull
    private String password;

    @NotNull
    private RegistrationType registrationType;
}
