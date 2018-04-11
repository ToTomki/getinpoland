package pl.getinpoland.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.getinpoland.dao.ArticleRepository;
import pl.getinpoland.dao.UserRepository;
import pl.getinpoland.model.article.Article;
import pl.getinpoland.model.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pl.getinpoland.model.user.enums.UserRole.CHIEF;
import static pl.getinpoland.model.user.enums.UserRole.REDACTOR;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/")
    public String mainPage(Model model){
        try{
        List<Article> tempList = new ArrayList<>();
        tempList = articleRepository.findLast7ByOrderByArticleId();
        model.addAttribute("firstTopArticle", tempList.get(0));
        model.addAttribute("secondTopArticle", tempList.get(1));
        model.addAttribute("articles", tempList.subList(2, 7));
        }
        catch(NullPointerException e){ //todo It is not recommended to catch NullPointerException! It should be changed!
            throw new NullPointerException("There is not enough articles on your site to present it correctly.");
        }
        return "mainPage";
    }

    @GetMapping("/{mainPageArticlePage}")
    public String mainPagePaging(Model model, @PathVariable("mainPageArticlePage") int articlePage){
        try{
        Page<Article> articles = articleRepository.findAll(new PageRequest(articlePage,5));
        model.addAttribute("articles", articles);
        model.addAttribute("number", articles.getNumber());
        model.addAttribute("pages",articles.getTotalPages());
    }
        catch(NullPointerException e){ //todo It is not recommended to catch NullPointerException! It should be changed!
            throw new NullPointerException("There is not enough articles on your site to present it correctly.");
        }
        return "mainPagePaging";
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
                listOfWorkers.get(i-1).setUserDescription("This person has no description yet.");
            }
        }
        model.addAttribute("listOfWorkers", listOfWorkers);
        return "aboutUs";
    }

    @GetMapping("/403")
    @ResponseBody
    public String accessDenied(){return "Access denied.";}

    @GetMapping("/aboutPoland")
    public String aboutPoland(){

        return "aboutPoland";
    }

    }


