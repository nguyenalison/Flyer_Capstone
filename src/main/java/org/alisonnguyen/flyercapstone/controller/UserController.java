package org.alisonnguyen.flyercapstone.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.alisonnguyen.flyercapstone.repository.CalendarRepository;
import org.alisonnguyen.flyercapstone.service.CalendarServiceImpl;
import org.alisonnguyen.flyercapstone.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
@Controller
@Slf4j
public class UserController {
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new
                StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    private UserServiceImpl userDetailsService;
    private CalendarServiceImpl calendarService;

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    public UserController(UserServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
//    @GetMapping("/")
//    private String redirectToLogin()
//    {
//        return "redirect:/login";
//    }
    @GetMapping("/sign-up")
    public String signUp(Model model)
    {
        model.addAttribute("userDto", new UserDTO());
        log.info("Sign up displayed");
        return "sign-up";
    }
    @PostMapping("/signup-process")
    public String signupProcess(@Valid @ModelAttribute ("userDto") UserDTO
                                        userDTO, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            log.warn("Wrong attempt");
            return "sign-up";
        }
        userDetailsService.create(userDTO);
        return "dashboard";
    }
    /**
     * In order to make code more readable it is good practice to
     * use special DTOs for login It also make controllers
     * less dependent from entities and separate validation from
     * jpa functionality
     */
    @GetMapping("/login")
    public String getLoginPage()
    {
        log.info("Login page displayed");
        return "login";
    }
    @RequestMapping("/home")
    public String getHome()
    {
        log.info("home page displayed");
        return "home";
    }

    @RequestMapping("/dashboard")
    public String getDashboard()
    {
        log.info("Dashboard displayed");
        return "dashboard";
    }
}