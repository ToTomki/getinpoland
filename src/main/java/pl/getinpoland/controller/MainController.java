package pl.getinpoland.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.getinpoland.dao.UserRepository;
import pl.getinpoland.model.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

import static pl.getinpoland.model.user.enums.UserRole.CHIEF;
import static pl.getinpoland.model.user.enums.UserRole.REDACTOR;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
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

    @GetMapping("/aboutUs")
    public String aboutUs(Model model){
        List<User> listOfWorkers = userRepository.findByUserRoleIn(Arrays.asList(REDACTOR, CHIEF));
        for (int i = listOfWorkers.size(); i > 0; i--){
            if (listOfWorkers.get(i-1).getUserDescription() == null){
                listOfWorkers.get(i-1).setUserDescription("This person has not a description yet.");
            }
        }
        model.addAttribute("listOfWorkers", listOfWorkers);
        return "aboutUs";
    }

    @GetMapping("/403")
    @ResponseBody
    public String accessDenied(){return "Access denied.";}

    @GetMapping("/startPoland")
    public String startPoland(){

        return "startPoland";
    }

    }


