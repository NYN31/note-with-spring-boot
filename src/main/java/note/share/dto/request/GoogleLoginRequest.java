package note.share.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import note.share.dto.Validator;

@Slf4j
@Getter
@Setter
@ToString
public class GoogleLoginRequest implements Validator {
    @Valid
    @NotNull
    @NotEmpty
    private String token;

    @Override
    public void RequestValidator() {
        log.info("google login validation check.");
        if(token.isEmpty()){
            log.info("google login token is null.");
            throw new RuntimeException("Required field should not null");
        }
    }
}