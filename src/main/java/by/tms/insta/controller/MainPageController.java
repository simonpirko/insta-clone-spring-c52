package by.tms.insta.controller;

import by.tms.insta.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mainPage")
public class MainPageController {

    @GetMapping
    public String mainPage(String name, Model model) {
        return "mainPage";
    }
}
