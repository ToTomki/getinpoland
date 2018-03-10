package pl.getinpoland.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.getinpoland.dao.UserRepository;
import pl.getinpoland.model.user.User;
import pl.getinpoland.model.user.UserForm;
import pl.getinpoland.model.user.enums.UserRole;

import javax.validation.Valid;
import java.util.*;

import static pl.getinpoland.model.user.enums.UserRole.CHIEF;
import static pl.getinpoland.model.user.enums.UserRole.REDACTOR;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String formRegister(Model model){
        model.addAttribute("user", new UserForm());
        return "user/register";
    }

    @ResponseBody
    @PostMapping("/register")
    public String registrationPost(@ModelAttribute("user") @Valid UserForm newUser, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "user/register";
        }

        User tempUser = userRepository.findByUsername(newUser.getUsername());
        if (tempUser != null) return "User name is occupied";
        User user = new User(newUser);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Registration has been successfully processed.";
    }

}
