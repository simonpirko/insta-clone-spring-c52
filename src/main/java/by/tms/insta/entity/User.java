package by.tms.insta.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

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
    @ManyToMany
    private List<User> followers;
    @ManyToMany
    private List<User> following;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}