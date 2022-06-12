package by.tms.insta.controller;

import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;
import by.tms.insta.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private PostService postService;


    @GetMapping
    public String getPost() {
        return "post";
    }

    @PostMapping(consumes = "multipart/form-data")
    public String savePost(@RequestPart(name = "file", required = false) MultipartFile imageFile) {
        Post newPost = new Post();
//        newPost.setImage(image);
        newPost.setUser(((User) httpSession.getAttribute("user")));
        postService.savePost(newPost);
        return "redirect:/";
    }
}