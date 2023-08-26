package note.share.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import note.share.constant.enums.RegistrationType;
import note.share.constant.enums.Role;
import note.share.constant.enums.Status;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "auth_user")
@Table
public class User extends DBCommon {
    @Size(min = 3, max = 60, message = "Name length should be between 3 and 60 characters")
    @Column(nullable = false)
    private String name;

    @Size(min = 3, max = 80, message = "Name length should be between 3 and 80 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 8, message = "Password length should be minimum 8 characters")
    @Column(nullable = false)
    private String password;

    private String profilePicture;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private RegistrationType registrationType;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Status userStatus;
}
