package by.tms.insta.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
public class User {

    @NotBlank
    @NotEmpty (message = "Имя пользователя не должно быть пустым")
    private String login;

    @NotBlank
    @NotEmpty (message = "Пароль не должен быть пустым")
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
