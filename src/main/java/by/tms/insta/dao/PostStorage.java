package by.tms.insta.dao;

import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;

import java.util.List;

public interface PostStorage {

    boolean savePost(Post post);

    List<Post> findAllPosts();

    List<Post> findPostsByUser(String login);

    boolean saveLike(Like like);

    List<Like> findAllLikes();

    List<Like> findLikesByPost(long id);

    List<Like> findLikesByUser(String login);

    boolean saveComment (Comment comment);

    List<Comment> findAllComments();

    List<Comment> findCommentsByUser(String login);

    List<Comment> findCommentsByPost(long id);
}
