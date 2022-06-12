
package by.tms.insta.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Email or phone number must not be empty")
    private String email;
    @NotBlank(message = "First and last name must not be empty")
    private String name;
    @NotBlank(message = "Username must not be empty")
    private String login;
    @NotBlank(message = "Password must not be empty")
    private String password;
    @Lob
    private byte[] photo;

    @OneToOne (cascade = CascadeType.ALL)
    Follower follower = new Follower();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany (cascade = CascadeType.ALL)
    private List<Follower> followers;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany (cascade = CascadeType.ALL)
    private List<Follower> following;

    public void setLogin(String login) {
        this.login = login;
        follower.setName(login);
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
        follower.setPhoto(photo);
    }

    public void setFollower(Follower follower) {
        follower.setName(this.login);
        follower.setPhoto(this.photo);
        this.follower = follower;
    }
}
