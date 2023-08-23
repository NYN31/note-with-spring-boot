package note.share.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import note.share.constant.ConstantValue;

@SuperBuilder
@Data
public class LoginResponse extends CommonResponse {
    private String token;

    public static LoginResponse form(String jwtToken) {
        return LoginResponse.builder()
                .code(200)
                .message(ConstantValue.OPERATION_SUCCESSFUL)
                .token(jwtToken)
                .build();
    }
}