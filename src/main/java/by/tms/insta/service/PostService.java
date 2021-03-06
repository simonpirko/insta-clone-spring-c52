package by.tms.insta.service;

import by.tms.insta.dao.PostStorage;
import by.tms.insta.entity.Comment;
import by.tms.insta.entity.Like;
import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PostService {
    private final PostStorage postStorage;

    @Autowired
    public PostService(@Qualifier("hibernatePostStorage") PostStorage postStorage) {
        this.postStorage = postStorage;
    }

    @Transactional
    public boolean savePost(Post post) {
        return postStorage.savePost(post);
    }

    public boolean deletePost(Post post) {
        if (postStorage.postExists(post.getUser())) {
            return postStorage.deletePost(post);
        }
        return false;
    }

    public List<Post> findAllPosts() {
        return postStorage.findAllPosts();
    }

    public List<Post> findPostsByUser(User user) {
        return postStorage.findPostsByUser(user);
    }

    public boolean saveLike(Like like) {
        if (postStorage.likeExists(like.getUser())) {
            return false;
        }
        return postStorage.saveLike(new Like());
    }

    public boolean deleteLike(Like like) {
        if (postStorage.likeExists(like.getUser())) {
            return postStorage.deleteLike(like);
        }
        return false;
    }

    public List<Like> findAllLikes() {
        return postStorage.findAllLikes();
    }

    public List<Like> findLikesByPost(Post post) {
        return postStorage.findLikesByPost(post);
    }

    public List<Like> findLikesByUser(User user) {
        return postStorage.findLikesByUser(user);
    }

    public boolean saveComment(Comment comment) {
        return postStorage.saveComment(comment);
    }

    public boolean deleteComment(Comment comment) {
        if (postStorage.commentExists(comment.getUser())) {
            return postStorage.deleteComment(comment);
        }
        return false;
    }

    public List<Comment> findAllComments() {
        return postStorage.findAllComments();
    }

    public List<Comment> findCommentsByUser(User user) {
        return postStorage.findCommentsByUser(user);
    }

    public List<Comment> findCommentsByPost(Post post) {
        return postStorage.findCommentsByPost(post);
    }
}