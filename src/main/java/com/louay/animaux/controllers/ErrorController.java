package com.louay.animaux.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError() {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("message", "Une erreur est survenue. Veuillez réessayer.");
        return mav;
    }
} 