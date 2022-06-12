package by.tms.insta.dao;

import by.tms.insta.entity.Comment;
import by.tms.insta.entity.Like;
import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;

import java.util.List;

public interface PostStorage {

    boolean savePost(Post post);

    boolean deletePost(Post post);

    boolean postExists(User user);

    List<Post> findAllPosts();

    List<Post> findPostsByUser(User user);

    boolean saveLike(Like like);

    boolean deleteLike(Like like);

    boolean likeExists(User user);

    List<Like> findAllLikes();

    List<Like> findLikesByPost(Post post);

    List<Like> findLikesByUser(User user);

    boolean saveComment (Comment comment);

    boolean deleteComment(Comment comment);

    boolean commentExists(User user);

    List<Comment> findAllComments();

    List<Comment> findCommentsByUser(User user);

    List<Comment> findCommentsByPost(Post post);
}
