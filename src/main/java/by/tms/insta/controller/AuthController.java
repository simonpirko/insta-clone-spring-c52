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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private static final String USER_DO_NOT_EXIST =
            "The username you entered does not belong to an account." +
                    "Check your username and try again.";

    private static final String PASSWORD_DO_NOT_EXIST = "Sorry, you entered the wrong password." +
            "Check your password again.";


    @Autowired
    HttpSession httpSession;
    @Autowired
    HibernateUserStorage hibernateUserStorage;

    @GetMapping
    public String auth(String name, Model model) {
        model.addAttribute("user", new User());
        return "sign-in";
    }

    @PostMapping
    public String auth(@Valid User user, BindingResult bindingResult,
                       Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        model.addAttribute(user);
        System.out.println(user);

        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            List<String> listMessage = new ArrayList<>();
            for (ObjectError a : allErrors) {
                listMessage.add(a.getDefaultMessage());
            }
            while (listMessage.remove("не должно быть пустым")) ;
            model.addAttribute("messages", listMessage);
            return "sign-in";
        } else {
            if (hibernateUserStorage.userIsExisted(user.getLogin(), user.getPassword())) {
                user = hibernateUserStorage.findUserByLogin(user.getLogin());
                model.addAttribute(user);
                httpSession.setAttribute("user", user);
                System.out.println(user);
                response.sendRedirect(request.getContextPath() + "/main");
            } else if (hibernateUserStorage.loginIsExisted(user.getLogin())) {
                model.addAttribute("message", PASSWORD_DO_NOT_EXIST);
                return "sign-in";
            } else if (!hibernateUserStorage.loginIsExisted(user.getLogin())) {
                model.addAttribute("message", USER_DO_NOT_EXIST);
                return "sign-in";
            }
        }
        return "sign-in";
    }
}


