package by.tms.insta.controller;

import by.tms.insta.entity.Comment;
import by.tms.insta.entity.Like;
import by.tms.insta.entity.Post;
import by.tms.insta.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public String getPost(String name, Model model) {
        model.addAttribute("post", new Post());
        return "post";
    }

    @PostMapping
    public String savePost(Post post) {
        postService.savePost(post);
        return "redirect:/main";
    }

    @PostMapping("/createComment")
    public String saveComment(Comment comment) {
        postService.saveComment(comment);
        return "redirect:/main";
    }

    @PostMapping("/deleteComment")
    public String deleteComment(Comment comment) {
        postService.deleteComment(comment);
        return "redirect:/main";
    }

    @PostMapping("/createLike")
    public String saveLike(Like like) {
        postService.saveLike(like);
        return "redirect:/main";
    }

    @PostMapping("/deleteLike")
    public String deleteLike(Like like) {
        postService.deleteLike(like);
        return "redirect:/main";
    }
}