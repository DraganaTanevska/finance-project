package mk.ukim.finki.web.financeproject.web;

import mk.ukim.finki.web.financeproject.model.Article;
import mk.ukim.finki.web.financeproject.model.enumerations.Source;
import mk.ukim.finki.web.financeproject.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(value = {"/","/home"})
    private String findAll(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to, @RequestParam(required = false) Source source,@RequestParam(required = false) Optional<Integer> page,
                           @RequestParam(required = false) Optional<Integer> size ,Model model) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<Article> article = articleService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("articles", articleService.findAll(from, to, source));
        model.addAttribute("Article",article);

        int totalPages = article.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "home.html";
    }
    @GetMapping("/onearticle/{id}")
    private String findOne(@PathVariable Long id,Model model){
        articleService.findById(id).ifPresent(o -> model.addAttribute("article",o));
        return "oneArticle.html";
    }

}
