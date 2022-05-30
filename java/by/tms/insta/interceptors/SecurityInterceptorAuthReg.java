package by.tms.insta.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class SecurityInterceptorAuthReg implements HandlerInterceptor {

    @Autowired
    HttpSession httpSession;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (httpSession.getAttribute("user") == null) {
           return true;
        } else {
                response.sendRedirect(request.getContextPath() + "/mainPage");
        }
    return false;
    }
}

