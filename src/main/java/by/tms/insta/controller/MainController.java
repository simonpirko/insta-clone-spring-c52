package by.tms.insta.controller;


import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;
import by.tms.insta.service.PostService;
import by.tms.insta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @GetMapping
    public String getFollowingsPost(Model model) {
        User user = (User) httpSession.getAttribute("user");
//        List<User> userFollowings = userService.getFollowings(user);
        List<Post> postFollowings = postService.findAllPosts();
        model.addAttribute("posts", postFollowings);
        return "index";
    }
}
