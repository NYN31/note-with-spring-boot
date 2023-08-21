package note.example.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class CommonResponse {
    public Integer code;
    public String status;
    public Object data;
}
