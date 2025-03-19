package com.warehouse.controller;

import com.warehouse.model.dto.UserDTO;
import com.warehouse.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author igoradulyan on 12/18/24
 * @project IntelliJ IDEA
 */
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerUser(Model model){
        model.addAttribute("user", new UserDTO());
        return "register-user";
    }

    @PostMapping("/register-process")
    public String registerProcess(@Valid @ModelAttribute ("user") UserDTO userDTO, BindingResult userBindingResult){
        if(userBindingResult.hasErrors()){
            return "register-user";
        }
        userService.saveUser(userDTO);
        return "redirect:/login";
    }
}
