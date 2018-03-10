package pl.getinpoland.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.getinpoland.dao.ArticleRepository;
import pl.getinpoland.model.article.Article;
import pl.getinpoland.model.article.ArticleForm;

import javax.validation.Valid;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/newArticle")
    public String newArticle(Model model){
        model.addAttribute("article", new ArticleForm());
        return "article/newArticle";
    }

    @ResponseBody
    @PostMapping("/newArticle")
    public String articlePost(@ModelAttribute("article") @Valid ArticleForm newArticle, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/article/newArticle";
        }
        Article article = new Article(newArticle);
        articleRepository.save(article);
        return "Article has been added";
    }
}
