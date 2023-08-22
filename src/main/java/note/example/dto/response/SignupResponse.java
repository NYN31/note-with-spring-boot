package note.example.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import note.example.constant.StringConstant;
import note.example.model.User;

@SuperBuilder
@Data
public class SignupResponse extends CommonResponse {
    private String name;
    private String email;
    private boolean isActive;

    public static SignupResponse form(User user) {
        return SignupResponse.builder()
                .code(200)
                .message(StringConstant.OPERATION_SUCCESSFUL)
                .name(user.name)
                .email(user.email)
                .isActive(user.isActive)
                .build();
    }
}
