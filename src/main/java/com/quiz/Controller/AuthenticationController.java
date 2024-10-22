package com.quiz.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/users")
public class AuthenticationController {
    @GetMapping(value = "/login")
    public ModelAndView loginController() {
        return new ModelAndView("/login");
    }
    @GetMapping(value = "/register")
    public ModelAndView registerController() {
        return new ModelAndView("/register");
    }
}
