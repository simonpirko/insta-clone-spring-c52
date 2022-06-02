package by.tms.insta.dao;

import by.tms.insta.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
    public boolean userExists(String login) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT COUNT(*) FROM User WHERE login = :login");
        boolean result = ((int) query.list().get(0) > 0) ? true : false;
        session.close();
        return result;
    }
}
