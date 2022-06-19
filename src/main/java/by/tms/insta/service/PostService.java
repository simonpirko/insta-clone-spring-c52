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

import java.util.ArrayList;
import java.util.List;

@Component
public class PostService {
    private final PostStorage postStorage;

    @Autowired
    public PostService(@Qualifier("hibernatePostStorage") PostStorage postStorage) {
        this.postStorage = postStorage;
    }

    @Transactional
    public boolean savePost(Post post){
        return postStorage.savePost(post);
    }

    public boolean deletePost(Post post){
        if (postStorage.postExists(post.getUser())){
            return postStorage.deletePost(post);
        }
        return false;
    }

    public List<Post> findAllPosts(){
        return postStorage.findAllPosts();
    }

    public List<Post> findPostsByUser(User user){
        return postStorage.findPostsByUser(user);
    }

    public boolean saveLike(Like like){
        if (postStorage.likeExists(like.getUser())){
            return false;
        }
        return postStorage.saveLike(new Like());
    }

    public boolean deleteLike(Like like){
        if (postStorage.likeExists(like.getUser())){
            return postStorage.deleteLike(like);
        }
        return false;
    }

    public List<Like> findAllLikes(){
        return postStorage.findAllLikes();
    }

    public List<Like> findLikesByPost(Post post){
        return postStorage.findLikesByPost(post);
    }

    public List<Like> findLikesByUser(User user){
        return postStorage.findLikesByUser(user);
    }

    public boolean saveComment(Comment comment){
        return postStorage.saveComment(comment);
    }

    public boolean deleteComment(Comment comment){
        if (postStorage.commentExists(comment.getUser())){
            return postStorage.deleteComment(comment);
        }
        return false;
    }

    public List<Comment> findAllComments(){
        return postStorage.findAllComments();
    }

    public List<Comment> findCommentsByUser(User user){
        return postStorage.findCommentsByUser(user);
    }

    public List<Comment> findCommentsByPost(Post post){
        return postStorage.findCommentsByPost(post);
    }

    public int getCountPosts(User user) {
        return postStorage.getCountPosts(user);
    }

    public List<Integer> getButtonForFirstEnter(User user) {
        List<Integer> listButton = new ArrayList<>();
        int countPosts = getCountPosts(user);
        int countButton = 0;
        if (countPosts < 11) return null;
        if ((countPosts % 10) == 0) countButton = countPosts / 10;
        else countButton = countPosts / 10 + 1;
        for (int i = 1; i <= countButton; i++) {
            listButton.add(i);
            if (i == 5) break;
        }
        return listButton;
    }

    public List<Integer> getButtonForNotFirstEnter(User user, int pressedButton) {
        List<Integer> listButton = new ArrayList<>();
        int countPosts = getCountPosts(user);
        int countButton = 0;
        if (countPosts < 11) return null;
        if ((countPosts % 10) == 0) countButton = countPosts / 10;
        else countButton = countPosts / 10 + 1;

        if ((countButton < 6) || (pressedButton < 3)) {
            for (int i = 1; i <= countButton; i++) {
                listButton.add(i);
                if (i == 5) break;
            }
        } else if ((countButton - pressedButton) >= 2) {
            for (int i = (pressedButton - 2); i <= (pressedButton + 2); i++) {
                listButton.add(i);
            }
        } else {
            for (int i = (countButton - 4); i <= countButton; i++) {
                listButton.add(i);
            }
        }
        return listButton;
    }

    public List<Post> getPostListForOnePage(User user, int pressedButton) {
        return postStorage.getPostListForOnePage(user,pressedButton);
    }

    public List<Post> getFirstTenFollowingPost(User user) {
        return postStorage.getFirstTenFollowingPost(user);
    }
}
