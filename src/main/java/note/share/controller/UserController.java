package note.share.controller;

import note.share.dto.request.PasswordUpdateRequest;
import note.share.dto.response.PasswordUpdateResponse;
import note.share.service.UserService;
import note.share.utility.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test() {
        return "hello";
    }

    @PostMapping("/update-password")
    public ResponseEntity<PasswordUpdateResponse> passwordUpdate(PasswordUpdateRequest request) {
        return Util.form(userService.passwordUpdate(request), HttpStatus.OK);
    }
}
