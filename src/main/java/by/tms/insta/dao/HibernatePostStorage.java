package by.tms.insta.dao;

import by.tms.insta.entity.Like;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HibernatePostStorage implements LikeStorage{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Like> findLikesByPost(Long postId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Like where postId =: pPostId",Like.class)
                .setParameter("pPostId",postId)
                .getResultList();

    }

    @Override
    public boolean saveLike(Like like) {
        try {
            sessionFactory.getCurrentSession().save(like);
        } catch (HibernateException e) {
            return false;
        }
        return true;

    }

    @Override
    public List<Like> findLikesByUser(Long userId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Like where userId =: pUserId",Like.class)
                .setParameter("pUserId",userId)
                .getResultList();
    }

    @Override
    public List<Like> findAllLikes() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Like",Like.class)
                .getResultList();
    }


}
