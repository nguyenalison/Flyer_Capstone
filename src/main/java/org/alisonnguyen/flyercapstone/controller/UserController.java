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

    @PostMapping("/loginprocessed")
    public String loginSuccess() {
        return "redirect:/dashboard";
    }
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

    @GetMapping("/login")
    public String getLoginPage()
    {
        log.info("Login page displayed");
        return "login";
    }

//    @GetMapping("/dashboard")
//    public String getDashboard(Model model)
//    {
//        log.info("Dashboard displayed");
//        return "dashboard";
//    }
}