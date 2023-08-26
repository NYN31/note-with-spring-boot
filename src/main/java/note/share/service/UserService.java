package note.share.service;

import note.share.model.User;

public interface UserService {
    void throwIfUserExistByEmail(String email);

    void throwIfUserExistByUsername(String username);

    User findUserById(Long userId);

    User findUserByEmail(String email);
}
