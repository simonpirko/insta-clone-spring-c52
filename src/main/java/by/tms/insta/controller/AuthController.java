package by.tms.insta.controller;

import by.tms.insta.entity.User;
import by.tms.insta.service.UserService;
import by.tms.insta.validators.UserDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private static final String USER_DO_NOT_EXIST =
            "The username you entered does not belong to an account. " +
                    "Check your username and try again.";

    private static final String PASSWORD_DO_NOT_EXIST = "Sorry, you entered the wrong password." +
            "Check your password again.";

    @Autowired
    UserService userservice;
    @Autowired
    UserDataValidator userDataValidator;

    @GetMapping
    public String auth(String name, Model model) {
        model.addAttribute("user", new User());
        return "sign-in";
    }

    @PostMapping
    public String auth(@Valid User user, BindingResult bindingResult,
                       Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        model.addAttribute(user);

        if (userDataValidator.hasAuthError(bindingResult)) {
            model.addAttribute("messages", userDataValidator.listErrorForAuth(bindingResult));
            return "sign-in";
        } else {
            if (userservice.authUserByLoginAndPass(user)) {
                user = userservice.findUserByLogin(user);
                model.addAttribute(user);
                request.getSession().setAttribute("user", user);
//                httpSession.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/");
            } else if (userservice.userExists(user.getLogin())) {
                model.addAttribute("message", PASSWORD_DO_NOT_EXIST);
                return "sign-in";
            } else {
                model.addAttribute("message", USER_DO_NOT_EXIST);
                return "sign-in";
            }
        }
        return "sign-in";
    }
}