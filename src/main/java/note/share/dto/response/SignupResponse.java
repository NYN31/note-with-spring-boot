package note.share.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import note.share.constant.ConstantValue;
import note.share.model.User;

@SuperBuilder
@Data
public class SignupResponse extends CommonResponse {
    private String name;
    private String email;
    private boolean isActive;

    public static SignupResponse form(User user) {
        return SignupResponse.builder()
                .code(200)
                .message(ConstantValue.OPERATION_SUCCESSFUL)
                .name(user.name)
                .email(user.email)
                .isActive(user.isActive)
                .build();
    }
}
