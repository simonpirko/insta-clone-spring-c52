package by.tms.insta.dao;

import by.tms.insta.entity.User;

import java.util.List;

/**
 * User storage.
 *
 * @author Stepan Vasilyeu
 * @since 28.06.2022
 */
public interface UserStorage {

    /**
     * Save user.
     *
     * @param user model to save
     * @return saved user model
     */
    User save(User user);

    /**
     * Find user by login.
     *
     * @param login the login
     * @return the user
     */
    User findUserByLogin(String login);

    /**
     * Find all users.
     *
     * @return users list
     */
    List<User> findAll();
}
