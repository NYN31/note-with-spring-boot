package note.share.dto.response;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class CommonResponse {
    protected Integer code;
    protected String message;

    public static CommonResponse form(Integer code, String message) {
        return CommonResponse.builder()
                .code(code)
                .message(message)
                .build();
    }
}
