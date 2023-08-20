package note.example.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CommonResponseDto {
    public Integer code;
    public String status;
    public Object data;
}
