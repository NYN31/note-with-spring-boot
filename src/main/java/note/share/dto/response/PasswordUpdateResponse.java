package note.share.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import note.share.constant.ConstantValue;

@SuperBuilder
@Data
public class PasswordUpdateResponse extends CommonResponse{
    public static PasswordUpdateResponse form() {
        return PasswordUpdateResponse.builder()
                .code(200)
                .message(ConstantValue.PASSWORD_UPDATE_SUCCESSFUL)
                .build();
    }
}
