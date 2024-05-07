package com.chi.demo.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;



@Controller
@SessionAttributes("username")
public class WelcomeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String gotoWelcomePage(ModelMap model) {
        model.put("username", getLoggedinUsername());
        return "welcome";
    }

    private String getLoggedinUsername() {
        // Type mismatch: cannot convert from Authentication to Neo4jProperties.AuthenticationJava(16777233) -> ???
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
