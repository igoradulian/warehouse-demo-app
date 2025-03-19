package com.warehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author igoradulyan on 12/18/24
 * @project IntelliJ IDEA
 */
@Controller
public class AuthenticationController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/home")
    public String home() {
        return "redirect:/register-product";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
