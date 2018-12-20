package com.dev.training.uownme.distribute.costs.controllers;

import com.dev.training.uownme.common.domain.User;
import com.dev.training.uownme.common.dto.UserDto;
import com.dev.training.uownme.common.repositories.UserRepository;
import com.dev.training.uownme.common.services.SecurityService;
import com.dev.training.uownme.common.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class DistributeCostsController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final SecurityService securityService;

    public DistributeCostsController(UserRepository userRepository,
                                     UserService userService,
                                     SecurityService securityService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.securityService = securityService;
    }


    @ResponseBody
    @GetMapping("/users")
    List<User> getAllUsers() {
        final List<User> all = userRepository.findAll();
        return all;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        final UserDto accountDto = new UserDto();
        model.addAttribute("userForm", accountDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") @Valid UserDto userForm,
                               BindingResult bindingResult,
                               Model model) {
        //TODO
//        userValidator.validate(userForm, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//
        userService.registerNewUserAccount(userForm);

        securityService.autoLogin(userForm.getUserName(), userForm.getPassword());

        return "redirect:/welcome";
    }


    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}
