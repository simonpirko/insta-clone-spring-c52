package by.tms.insta.storages;

import by.tms.insta.entity.User;

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


    /**
     * @return check existence user
     */
    boolean userIsExisted(String login, String password);
}

