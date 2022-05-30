package by.tms.insta.storages;

import by.tms.insta.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HibernateUserStorage implements UserStorage {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean userIsExisted(String login, String password) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("from User where login = :login" +
                " and password= :password", User.class);
        query.setParameter("login", login);
        query.setParameter("password", password);
        boolean result = !query.list().isEmpty();
        session.close();
        return result;
    }

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
