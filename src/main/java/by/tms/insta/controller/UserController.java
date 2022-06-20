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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user-info")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ImageUtil imageUtil;

    @GetMapping
    public String getUser(Model model){
        String login=((User) httpSession.getAttribute("user")).getLogin();
        User user=userService.findUserByLogin(login);
        model.addAttribute("user",user);
        List<Post> postFollowings = postService.findAllPosts();
        model.addAttribute("posts", postFollowings);
        model.addAttribute("imgUtil", imageUtil);
        return "user-info";
    }

    @PostMapping(consumes = "multipart/form-data")
    public String savePostUser(@RequestParam(value = "image") MultipartFile image,
                           String description,
                           HttpServletRequest request) {
        Post myPost = new Post();
        try {
            myPost.setImage(image.getBytes());
        } catch (IOException e) {
            return "user-info";
        }
        User sessionUser = (User) request.getSession().getAttribute("user");
        myPost.setUser(sessionUser);
        myPost.setDescription(description);
        postService.savePost(myPost);
        return "redirect:/user-info";
    }

}
