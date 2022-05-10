package mk.ukim.finki.web.financeproject.web;

import mk.ukim.finki.web.financeproject.model.Article;
import mk.ukim.finki.web.financeproject.service.ArticleService;
import mk.ukim.finki.web.financeproject.service.SourceService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;
    private final SourceService sourceService;

    public ArticleController(ArticleService articleService, SourceService sourceService) {
        this.articleService = articleService;
        this.sourceService = sourceService;
    }

    @GetMapping("/")
    private String findAll(@RequestParam(required = false) Date from, @RequestParam(required = false) Date to, @RequestParam(required = false) Long sourceId, Model model) {
        model.addAttribute("articles", articleService.findAll(from, to, sourceId));
        model.addAttribute("sources", sourceService.findAll());
        return "home";
    }

}
