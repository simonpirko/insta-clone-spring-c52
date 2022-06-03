package by.tms.insta.controller;

import by.tms.insta.dao.PostStorage;
import by.tms.insta.dao.UserStorage;
import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;
import by.tms.insta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @GetMapping
    public String getFollowingsPost (User user, Model model) {
    model.addAttribute("user", user);
    List<User> userFollowings = userService.getFollowings(user);
    List<Post> postFollowings;
    for (User following : userFollowings) {
        postFollowings = postService.findPostsByUser(following);
        for (Post post : postFollowings) {
            model.addAttribute(post);
        }
    }
        return "index";
    }
}
