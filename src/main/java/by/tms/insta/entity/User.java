package by.tms.insta.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity @NoArgsConstructor
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String name;
    private String login;
    private String password;
    @Lob
    @Column(name = "photo", columnDefinition="BLOB")
    private byte[] photo;
    @ManyToMany
    @JoinTable (name="users",
            joinColumns=@JoinColumn (name="users_id"),
            inverseJoinColumns=@JoinColumn(name="users_id"))
    private List<User> followers;
    @ManyToMany
    @JoinTable (name="users",
            joinColumns=@JoinColumn (name="users_id"),
            inverseJoinColumns=@JoinColumn(name="users_id"))
    private List<User> following;
}