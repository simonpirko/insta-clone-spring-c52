package by.tms.insta.controller;

import by.tms.insta.entity.Post;
import by.tms.insta.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

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
    public String savePost(@RequestParam(value = "image") MultipartFile image) {
        Post newPost = new Post();
        try {
            newPost.setImage(image.getBytes());
        } catch (IOException e){
            return "post";
        }
//        User sessionUser = (User) httpSession.getAttribute("user");
//        newPost.setUser(user);
        postService.savePost(newPost);
        System.out.println(postService.findAllPosts());
        return "redirect:/";
    }
}