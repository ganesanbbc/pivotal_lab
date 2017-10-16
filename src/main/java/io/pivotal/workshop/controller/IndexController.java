package io.pivotal.workshop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class IndexController {

    @Value("${message: default message}")
    private String message;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/message")
    public ModelAndView message() {

        ModelAndView model = new ModelAndView("message");
        model.addObject("message", "This is a Spring Boot app");
        return model;
    }



}