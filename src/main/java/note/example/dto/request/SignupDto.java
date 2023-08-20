package note.example.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class SignupDto {
    @Length(min=3, max=40)
    public String name;

    public String email;

    @Length(min=8, max=40)
    public String password;
}
