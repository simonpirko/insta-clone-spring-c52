package by.tms.insta.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity @NoArgsConstructor
@Table (name = "users")
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
    @ManyToMany (cascade = CascadeType.ALL)
    private List<Follower> followers;
    @ManyToMany (cascade = CascadeType.ALL)
    private List<Follower> following;

    public List<Follower> getFollowers() {
        return new ArrayList<>();
    }

    public List<Follower> getFollowing() {
        return new ArrayList<>();
    }
}