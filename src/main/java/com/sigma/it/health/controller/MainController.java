package com.sigma.it.health.controller;


import com.sigma.it.health.model.User;
import com.sigma.it.health.service.UserRegistrationDto;
import com.sigma.it.health.service.UserService;
import com.sigma.it.health.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class MainController {

    @Autowired
    private UserService userService;


    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public String root() {


        return "index";
    }


    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }


    @GetMapping("/profile")
    public String currentUser(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result, Model model) {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();


        User user = userRepository.findByEmail(email);
        String firstName = user.getFirstName();
        model.addAttribute("firstName", firstName);
        model.addAttribute("email", email);


        return "profile";

    }

    @RequestMapping("/home")
    public String home(){

        return "home";
    }
}
