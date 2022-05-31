package by.tms.insta.dao;

import by.tms.insta.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HibernateUserStorage implements UserStorage{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean save(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
        return true;
    }

    @Override
    public User findUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        User userByLogin = session
                .createQuery("from User where login = :login", User.class)
                .setParameter("login", login)
                .getSingleResult();
        session.close();
        return userByLogin;
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        List<User> resultList = session
                .createQuery("from User", User.class).getResultList();
        session.close();
        return resultList;
    }

    @Override
    public boolean userExists(User user) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("from User where login = :login and password = :password", User.class);
        query.setParameter("login", user.login);
        query.setParameter("password", user.password);
        boolean result = !query.list().isEmpty();
        session.close();
        return result;
    }
}
