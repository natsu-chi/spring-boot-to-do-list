package com.chi.demo.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private AuthenticationService authenticationService;
    public LoginController() {
        super();
        this.authenticationService = new AuthenticationService();
    }

    @RequestMapping("/login-in-html")
    public String loginInHtml() {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>Login Page</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<h1>Login Page</h1>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    @RequestMapping("/login-ver1")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/login-ver2-with-params")
    public String loginWithParams(@RequestParam String username, ModelMap model) {
        model.put("username", username);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginFormal() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginValidated(@RequestParam String username, @RequestParam String password, ModelMap model) {

        model.put("username", username);
        boolean isValid = authenticationService.authenticate(username, password);

        if (isValid) {
            return "welcome";
        } else {
            model.put("username", "");
            model.put("errorMsg", "Invalid Credentials. Please try again!");
            return "login";
        }
    }
}
