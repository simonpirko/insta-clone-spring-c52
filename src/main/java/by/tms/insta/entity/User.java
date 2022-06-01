package by.tms.insta.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Entity @NoArgsConstructor
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String name;
    @NotBlank
    @NotEmpty(message = "Username must not be empty")
    private String login;
    @NotBlank
    @NotEmpty (message = "Password must not be empty")
    private String password;
    @Lob
    @Column(columnDefinition="BLOB")
    private byte[] photo;
    @ManyToMany
    private List<User> followers;
    @ManyToMany
    private List<User> following;
}