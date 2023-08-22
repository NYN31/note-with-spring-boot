package note.example.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import note.example.constant.StringConstant;

@SuperBuilder
@Data
public class LoginResponse extends CommonResponse {
    private String token;

    public static LoginResponse form(String jwtToken) {
        return LoginResponse.builder()
                .code(200)
                .message(StringConstant.OPERATION_SUCCESSFUL)
                .token(jwtToken)
                .build();
    }
}
