package note.share.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import note.share.constant.ConstantValue;
import note.share.model.User;

@SuperBuilder
@Data
public class RegistrationResponse extends CommonResponse {
    private String name;
    private String username;
    private String email;

    public static RegistrationResponse form(User user) {
        return RegistrationResponse.builder()
                .code(200)
                .message(ConstantValue.OPERATION_SUCCESSFUL)
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
