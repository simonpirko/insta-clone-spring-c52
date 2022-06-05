package by.tms.insta.validators;

import by.tms.insta.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDataValidator {



    private static final String NOT_EMAIL_OR_PHONE = "The expression you entered is not email" +
            " or phone number";

    private static final String ERROR_LOGIN =  "The username you entered does not" +
            " meet the established rules";

    private static final String ERROR_PASSWORD =  "The password you entered does not" +
            " meet the established rules (must contain at least 6 characters" +
            " and must not contain spaces)";

    public boolean hasAuthError(BindingResult bindingResult) {
        return bindingResult.getFieldErrorCount() > 2;
    }

    public List<String> listErrorForAuth(BindingResult bindingResult) {
        List<FieldError> allErrors = bindingResult.getFieldErrors();
        List<String> listMessage = new ArrayList<>();
        for (FieldError a : allErrors) {
            if ((a.getField().equals("password")) || (a.getField().equals("login")))
                listMessage.add(a.getDefaultMessage());
        }
        return listMessage;
    }

    public boolean hasRegError(BindingResult bindingResult, User user) {
        return (bindingResult.getFieldErrorCount() > 0 ||
                errorEmailOrPhone(user.getEmail()) ||
                errorPassword(user.getPassword()) ||
                errorLogin (user.getLogin()));
    }

    public List<String> listErrorForReg(BindingResult bindingResult, User user) {
        List<FieldError> allErrors = bindingResult.getFieldErrors();
        List<String> listMessage = new ArrayList<>();
        List<String> listFieldName = new ArrayList<>();
        for (FieldError a : allErrors) {
            listMessage.add(a.getDefaultMessage());
            listFieldName.add(a.getField());
        }
        if (!listFieldName.contains("email")&&errorEmailOrPhone (user.getEmail()))
            listMessage.add(NOT_EMAIL_OR_PHONE);
        if (!listFieldName.contains("login")&&errorLogin (user.getLogin()))
            listMessage.add(ERROR_LOGIN);
        if (!listFieldName.contains("password")&&errorPassword (user.getPassword()))
            listMessage.add(ERROR_PASSWORD);
        return listMessage;
    }

    public boolean errorEmailOrPhone (String fieldValue) {
        return (!fieldValue.matches("(((\\+375)|(80))((25)|(33)|(44)|(29))[1-9]\\d{6})|" +
                "([a-z0-9_-]+@[a-z]{2,}\\.[a-z]{2,6})"));
    }

    public boolean errorPassword (String fieldValue) {
        return (fieldValue.length()<6||fieldValue.contains(" "));
    }

    public boolean errorLogin (String fieldValue) {
        if (fieldValue.length()>31||fieldValue.length()<3||
                fieldValue.contains(" ")||fieldValue.contains("..")||fieldValue.charAt(0)=='.'||
                fieldValue.charAt(fieldValue.length()-1)=='.') return true;
        return (!fieldValue.matches("[a-z._]+"));
    }
}
