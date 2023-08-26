package note.share.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class UserDetails extends DBCommon{

    @Size(max = 200)
    private String location;

    @Size(max = 200)
    private String aboutMe;

    @Size(max = 200)
    private String skills;

    @Size(max = 200)
    private String education;

    @Size(max = 200)
    private String work;

    @Size(max = 80)
    private String github;

    @Size(max = 100)
    private String linkedIn;

    @Size(max = 100)
    private String portfolio;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}