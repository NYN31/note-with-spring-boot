package note.share.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PasswordUpdateRequest {
    @Size(min = 8, max = 40)
    @NotNull
    private String currentPassword;

    @Size(min = 8, max = 40)
    @NotNull
    private String newPassword;
}
