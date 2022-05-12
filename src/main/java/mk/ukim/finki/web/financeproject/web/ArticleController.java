package mk.ukim.finki.web.financeproject.web;

import mk.ukim.finki.web.financeproject.model.enumerations.Source;
import mk.ukim.finki.web.financeproject.service.ArticleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    private String findAll(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to, @RequestParam(required = false) Source source, Model model) {
        model.addAttribute("articles", articleService.findAll(from, to, source));
        return "home.html";
    }

}
