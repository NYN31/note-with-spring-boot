package note.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "auth_user")
@Table
public class User extends BasicAttribute {
    public String name;

    @Column(unique = true)
    public String email;

    public String password;

    public boolean isActive;
}
