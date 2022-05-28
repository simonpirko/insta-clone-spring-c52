package by.tms.insta.dao;

import by.tms.insta.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HibernateUserStorage implements UserStorage{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
    }

    @Override
    public User findUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        User singleResult = session
                .createQuery("from User where login = :login", User.class)
                .setParameter("login", login)
                .getSingleResult();
        session.close();
        return singleResult;
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        List<User> resultList = session
                .createQuery("from User", User.class).getResultList();
        session.close();
        return resultList;
    }
}
