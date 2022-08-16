package mk.ukim.finki.web.financeproject.web;

import mk.ukim.finki.web.financeproject.model.Article;
import mk.ukim.finki.web.financeproject.model.dto.SpecificEntity;
import mk.ukim.finki.web.financeproject.model.enumerations.SourceApi;
import mk.ukim.finki.web.financeproject.model.exceptions.ArticleNotFoundException;
import mk.ukim.finki.web.financeproject.service.ArticleService;
import mk.ukim.finki.web.financeproject.service.NamedEntityService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;
    private final NamedEntityService namedEntityService;

    public ArticleController(ArticleService articleService, NamedEntityService namedEntityService) {
        this.articleService = articleService;
        this.namedEntityService = namedEntityService;
    }

    @GetMapping(value = {"/", "/home"})
    private String findAll(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to,
            @RequestParam(required = false) SourceApi sourceApi,
            @RequestParam(required = false) String sentiment,
            @RequestParam(required = false) List<String> entityLabels,
            @RequestParam(required = false) String specificEntityLabel,
            @RequestParam(required = false) String specificEntityWord,
            Model model
    ) {
        SpecificEntity specificEntity = null;

        if (specificEntityLabel != null && specificEntityWord != null && !specificEntityLabel.equals("") && !specificEntityWord.equals("") ) {
            specificEntity = new SpecificEntity(specificEntityLabel, specificEntityWord);
        }

        List<Article> articles = articleService.findAll(from, to, sourceApi, sentiment, entityLabels, specificEntity)
                .stream()
                .limit(10)
                .collect(Collectors.toList());
        // TODO: 15.08.2022 fix pagination instead of limit(10) :D

        model.addAttribute("articles", articles);
        model.addAttribute("namedEntities", namedEntityService.getAllEntities());
        return "home";
    }

    @GetMapping("/onearticle/{id}")
    private String findOne(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.findById(id).orElseThrow(() -> new ArticleNotFoundException(id)));
        return "oneArticle";
    }

}
