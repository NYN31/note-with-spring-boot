package note.example.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class CommonResponse {
    protected Integer code;
    protected String message;
}
