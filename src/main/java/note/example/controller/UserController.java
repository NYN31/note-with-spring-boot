package note.example.controller;

import note.example.dto.response.CommonResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/test")
    public CommonResponseDto test() {
        CommonResponseDto response = new CommonResponseDto();
        response.setCode(200);
        response.setStatus("Success");
        return response;
    }
}
