package com.quiz.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class ClientController {
    @GetMapping(value = {"/", "/home"})
    public ModelAndView clientController() {
        return new ModelAndView("/client/index");
    }
}
