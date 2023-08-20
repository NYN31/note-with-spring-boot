package note.example.dto.request;

import org.hibernate.validator.constraints.Length;

public class LoginDto {
    public String email;

    @Length(min=8, max=40)
    public String password;
}
