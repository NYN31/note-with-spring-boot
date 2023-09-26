package note.share.dto.response;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GoogleLoginUserResponse {
    private String name;
    private String email;

    public static GoogleLoginUserResponse form(Payload payload) {
        return GoogleLoginUserResponse.builder()
                .name((String) payload.get("name"))
                .email(payload.getEmail())
                .build();
    }
}
