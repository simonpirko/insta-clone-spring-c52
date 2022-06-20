package by.tms.insta.dao;

import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserStorage {

    /**
     * @param user model to save
     * @return saved user model
     */
    boolean save(User user);

    /**
     * @param login the login
     * @return the user
     */
    User findUserByLogin(String login);

    /**
     * @return users list
     */
    List<User> findAll();

    boolean userExists(String login);

    void addFollower(User userFollower, User user);

    void update(String login, User newUser,
                HttpServletRequest request, HttpServletResponse response);

    void deleteFollower(User userFollower, User user);

}

