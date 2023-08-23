package note.share.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "auth_user")
@Table
public class User extends BasicAttribute {
    public String name;

    @Column(unique = true)
    public String email;

    public String password;

    public boolean isActive;
}
