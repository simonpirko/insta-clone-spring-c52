package by.tms.insta.dao;

import by.tms.insta.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class HibernatePostStorage implements PostStorage {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean savePost(Post post) {
        Session session = sessionFactory.openSession();
        post.setDateTime(ZonedDateTime.now());
        session.save(post);
        session.close();
        return true;
    }

    @Override
    public boolean deletePost(Post post) {
        Session session = sessionFactory.openSession();
        session.delete(post);
        session.close();
        return true;
    }

    @Override
    public boolean postExists(User user) {
        Session session = sessionFactory.openSession();
        Long totalPosts = (Long) session
                .createQuery("SELECT COUNT(*) FROM Post WHERE user = :user")
                .getSingleResult();
        session.close();
        return totalPosts > 0;
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
    public boolean deleteLike(Like like) {
        Session session = sessionFactory.openSession();
        session.delete(like);
        session.close();
        return true;
    }

    @Override
    public boolean likeExists(User user) {
        Session session = sessionFactory.openSession();
        Long totalLikes = (Long) session
                .createQuery("SELECT COUNT(*) FROM Like WHERE user = :user")
                .getSingleResult();
        session.close();
        return totalLikes > 0;
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
        comment.setDateTime(ZonedDateTime.now());
        session.save(comment);
        session.close();
        return true;
    }

    @Override
    public boolean deleteComment(Comment comment) {
        Session session = sessionFactory.openSession();
        session.delete(comment);
        session.close();
        return true;
    }

    @Override
    public boolean commentExists(User user) {
        Session session = sessionFactory.openSession();
        Long totalComments = (Long) session
                .createQuery("SELECT COUNT(*) FROM Comment WHERE user = :user")
                .getSingleResult();
        session.close();
        return totalComments > 0;
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

    @Override
    public List<BigInteger> getListIdPosts(User user) {
        Session session = sessionFactory.openSession();
        List<BigInteger> result = new ArrayList<>();
        user = session.get(User.class, user.getId());

        for (Follower follower : user.getFollowing()) {
            List<BigInteger> idPostOneFollowing = session.createSQLQuery(
                            "select ID from POST p where p.USER_ID = :id")
                    .setParameter("id", follower.getId()).list();
            result.addAll(idPostOneFollowing);
        }
        session.close();
        Collections.sort(result);
        Collections.reverse(result);
        return result;
    }

    @Override
    public int getCountPosts(User user) {
        Session session = sessionFactory.openSession();
        int result = 0;
        user = session.get(User.class, user.getId());
        for (Follower follower : user.getFollowing()) {
            String box = String.valueOf(session.createSQLQuery(
                            "select COUNT(*) from POST p where p.USER_ID = :id")
                    .setParameter("id", follower.getId()).getSingleResult());
            result = result + Integer.parseInt(box);
        }
        session.close();
        return result;
    }

    @Override
    public List<Post> getPostListForOnePage(User user, int pressedButton) {
        Session session = sessionFactory.openSession();
        List<Post> listPostForOnePage = new ArrayList<>();
        List<BigInteger> idPostAllFollowing = getListIdPosts(user);
        int countPosts = getCountPosts(user);
        if ((countPosts - (pressedButton-1) * 10) > 9) {
            for (int i = (pressedButton * 10 - 10); i < pressedButton * 10; i++) {
                Post post = new Post();
                post = session.get(Post.class, idPostAllFollowing.get(i).longValue());
                listPostForOnePage.add(post);
            }
        } else  {
            for (int i = (pressedButton * 10 - 10); i < countPosts; i++) {
                Post post = new Post();
                post = session.get(Post.class, idPostAllFollowing.get(i).longValue());
                listPostForOnePage.add(post);
            }
        }
        session.close();
        return listPostForOnePage;
    }

    @Override
    public List<Post> getFirstTenFollowingPost(User user) {
        Session session = sessionFactory.openSession();
        List<Post> listPost = new ArrayList<>();
        List<BigInteger> listIdPosts = getListIdPosts(user);
        for (int i = 0; i < listIdPosts.size(); i++) {
            Post post = new Post();
            post = session.get(Post.class, listIdPosts.get(i).longValue());
            listPost.add(post);
            if (i==9) break;
        }
        session.close();
        return listPost;
    }
}

