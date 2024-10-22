package com.quiz.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "${admin.prefix}")
public class AdminController {

    @GetMapping(value = "/")
    public ModelAndView dashboard() {
        return new ModelAndView("/admin/dashboard");
    }
}
