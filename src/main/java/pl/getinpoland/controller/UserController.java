package pl.getinpoland.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.getinpoland.dao.UserRepository;
import pl.getinpoland.model.user.User;
import pl.getinpoland.model.user.UserForm;

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
    public String registrationSuccess(@ModelAttribute("user") UserForm newUser){
        User user = new User(newUser);
        User tempUser = userRepository.findByUsername(user.getUsername());
        if (tempUser != null) return "User name is occupied";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Registration has been successfully processed.";
    }
}
