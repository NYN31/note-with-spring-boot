package note.share.service;

import note.share.dto.request.PasswordUpdateRequest;
import note.share.dto.response.PasswordUpdateResponse;
import note.share.model.User;

public interface UserService {
    PasswordUpdateResponse passwordUpdate(PasswordUpdateRequest request);
    void throwIfUserExistByEmail(String email);

    void throwIfUserExistByUsername(String username);

    User findUserById(Long userId);

    User findUserByEmail(String email);
}
