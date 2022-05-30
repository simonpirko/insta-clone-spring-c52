package by.tms.insta.dao;

import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;

import java.util.List;

public interface PostStorage {

    boolean savePost(Post post);

    List<Post> findAllPosts();

    Post findPostsByUser (User user);

    boolean saveLike(Like like);

    List<Like> findAllLikes();

    Like findLikesByPost (Post post);

    Like findLikesByUser (User user);

    boolean saveComment (Comment comment);

    List<Comment> findAllComments();

    Comment findCommentsByUser (User user);

    Comment findCommentsByPost (Post post);
}
