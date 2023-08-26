package note.share.controller;

import note.share.dto.response.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {
    @GetMapping("/test")
    public String test() {
        return "hello";
    }
}
