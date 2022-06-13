package by.tms.insta.controller;


import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;
import by.tms.insta.service.PostService;
import by.tms.insta.service.UserService;
import by.tms.insta.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @Autowired
    private ImageUtil imageUtil;

    @GetMapping
    public String getFollowingsPost(Model model,
                                    HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
//        List<User> userFollowings = userService.getFollowings(user);
        List<Post> postFollowings = postService.findAllPosts();
        model.addAttribute("posts", postFollowings);
        model.addAttribute("imgUtil", imageUtil);
        return "index";
    }
}
