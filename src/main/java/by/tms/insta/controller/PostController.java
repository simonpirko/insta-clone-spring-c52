package by.tms.insta.controller;

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
        return "redirect:/index";
    }
}
