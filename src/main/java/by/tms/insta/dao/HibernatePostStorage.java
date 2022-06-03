package by.tms.insta.dao;

import by.tms.insta.entity.Comment;
import by.tms.insta.entity.Like;
import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HibernatePostStorage implements PostStorage {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean savePost(Post post) {
        Session session = sessionFactory.openSession();
        session.save(post);
        session.close();
        return true;
    }

    @Override
    public List<Post> findAllPosts() {
        Session session = sessionFactory.openSession();
        List<Post> allPostsList = session
                .createQuery("from Post", Post.class)
                .getResultList();
        session.close();
        return allPostsList;
    }

    @Override
    public List<Post> findPostsByUser(User user) {
        Session session = sessionFactory.openSession();
        List<Post> allPostsByUser = session
                .createQuery("from Post where user = :user", Post.class)
                .setParameter("user", user)
                .getResultList();
        session.close();
        return allPostsByUser;
    }

    @Override
    public boolean saveLike(Like like) {
        Session session = sessionFactory.openSession();
        session.save(like);
        session.close();
        return true;
    }

    @Override
    public List<Like> findAllLikes() {
        Session session = sessionFactory.openSession();
        List<Like> allLikesList = session
                .createQuery("from Like", Like.class)
                .getResultList();
        session.close();
        return allLikesList;
    }

    @Override
    public List<Like> findLikesByPost(Post post) {
        Session session = sessionFactory.openSession();
        List<Like> allLikesByPost = session
                .createQuery("from Like where post = :post", Like.class)
                .setParameter("post", post)
                .getResultList();
        session.close();
        return allLikesByPost;
    }

    @Override
    public List<Like> findLikesByUser(User user) {
        Session session = sessionFactory.openSession();
        List<Like> allLikesByUser = session
                .createQuery("from Like where user = :user", Like.class)
                .setParameter("user", user)
                .getResultList();
        session.close();
        return allLikesByUser;
    }

    @Override
    public boolean saveComment(Comment comment) {
        Session session = sessionFactory.openSession();
        session.save(comment);
        session.close();
        return true;
    }

    @Override
    public List<Comment> findAllComments() {
        Session session = sessionFactory.openSession();
        List<Comment> allCommentsList = session
                .createQuery("from Comment", Comment.class)
                .getResultList();
        session.close();
        return allCommentsList;
    }

    @Override
    public List<Comment> findCommentsByUser(User user) {
        Session session = sessionFactory.openSession();
        List<Comment> allCommentsByUser = session
                .createQuery("from Comment where user = :user", Comment.class)
                .setParameter("user", user)
                .getResultList();
        session.close();
        return allCommentsByUser;
    }

    @Override
    public List<Comment> findCommentsByPost(Post post) {
        Session session = sessionFactory.openSession();
        List<Comment> allCommentsByPost = session
                .createQuery("from Comment where post = :post", Comment.class)
                .setParameter("post", post)
                .getResultList();
        session.close();
        return allCommentsByPost;
    }
}