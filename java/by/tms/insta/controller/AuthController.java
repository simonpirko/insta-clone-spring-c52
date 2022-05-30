package by.tms.insta.controller;

import by.tms.insta.entity.User;
import by.tms.insta.dao.HibernateUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private static final String USER_DO_NOT_EXIST = "Неправильно введены логин или пароль";

    @Autowired
    HttpSession httpSession;
    @Autowired
    HibernateUserStorage hibernateUserStorage;

    @GetMapping
    public String auth(String name, Model model) {
        model.addAttribute("user", new User());
        return "auth";
    }

    @PostMapping
    public String auth(@Valid User user, BindingResult bindingResult,
                       Model model) {
        model.addAttribute(user);

        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            List<String> listMessage = new ArrayList<>();
            for (ObjectError a : allErrors) {
                listMessage.add(a.getDefaultMessage());
            }
            while (listMessage.remove("не должно быть пустым")) ;
            model.addAttribute("messages", listMessage);
            return "auth";
        } else {
            if (hibernateUserStorage.userIsExisted(user.getLogin(), user.getPassword())) {
                user = hibernateUserStorage.findUserByLogin(user.getLogin());
                model.addAttribute(user);
                httpSession.setAttribute("user", user);
                System.out.println(user);
                return "mainPage";
            } else {
                model.addAttribute("messages", USER_DO_NOT_EXIST);
                return "auth";
            }
        }
    }
}

