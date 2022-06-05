package by.tms.insta.controller;

import by.tms.insta.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
public class LogOutController {

    @Autowired
    HttpSession httpSession;

    @GetMapping
    public String logout(String name, Model model) {
        httpSession.invalidate();
        model.addAttribute("user", new User());
        return "sign-in";
    }
}
