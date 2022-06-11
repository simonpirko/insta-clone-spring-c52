package by.tms.insta.controller;


import by.tms.insta.entity.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String getFollowingsPost (List<Post> postList, Model model) {
        model.addAttribute("posts", postList);
        return "index";
    }
}
