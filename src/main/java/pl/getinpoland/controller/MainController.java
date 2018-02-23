package pl.getinpoland.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.getinpoland.model.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {

    @GetMapping(value = {"/",""})
    public String mainPage(Model model){
        return "mainPage";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/failureLogin")
    @ResponseBody
    public String failureLogin(){
        return "Logging in was not successful";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/loggedin")
    public String loggedin() {
        return "loggedin";
    }

    @GetMapping("/outlogged")
    public String outlogged(Model model){
        return "redirect:/";
    }

    }


