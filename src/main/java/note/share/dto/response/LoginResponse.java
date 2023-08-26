package note.share.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import note.share.constant.ConstantValue;

@SuperBuilder
@Data
public class LoginResponse extends CommonResponse {
    private String name;
    private String username;
    private String email;
    private String token;

    public static LoginResponse form(String name, String username, String email, String jwtToken) {
        return LoginResponse.builder()
                .code(200)
                .message(ConstantValue.OPERATION_SUCCESSFUL)
                .name(name)
                .username(username)
                .email(email)
                .token(jwtToken)
                .build();
    }
}
