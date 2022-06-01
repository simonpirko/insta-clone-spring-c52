package by.tms.insta.controller;


import by.tms.insta.dao.HibernateUserStorage;
import by.tms.insta.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/registration")
public class RegController {

    private static final String USER_IS_EXIST = "User with the same name already exists." +
            " Please enter a different username";

    @Autowired
    HibernateUserStorage hibernateUserStorage;

    @GetMapping
    public String reg(String name, Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }



    @PostMapping
    public String reg(@Valid User user, BindingResult bindingResult,
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
            return "sign-up";
        } else {
            if (hibernateUserStorage.loginIsExisted(user.getLogin())) {
                model.addAttribute("message", USER_IS_EXIST);
                return "sign-up";
            } else {
                hibernateUserStorage.save(user);
                model.addAttribute("user", user);
                return "sign-in";
            }
        }
    }
}
