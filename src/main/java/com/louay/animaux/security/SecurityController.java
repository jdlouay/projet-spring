package com.louay.animaux.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/accessDenied")
    public String error() {
        return "accessDenied";
    }
}