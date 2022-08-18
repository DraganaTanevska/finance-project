package mk.ukim.finki.web.financeproject.web;

import mk.ukim.finki.web.financeproject.model.Article;
import mk.ukim.finki.web.financeproject.model.dto.EntityFilterDto;
import mk.ukim.finki.web.financeproject.model.dto.FilterArticleDto;
import mk.ukim.finki.web.financeproject.model.enumerations.SourceApi;
import mk.ukim.finki.web.financeproject.model.exceptions.ArticleNotFoundException;
import mk.ukim.finki.web.financeproject.service.ArticleService;
import mk.ukim.finki.web.financeproject.service.NamedEntityService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final NamedEntityService namedEntityService;

    public ArticleController(ArticleService articleService, NamedEntityService namedEntityService) {
        this.articleService = articleService;
        this.namedEntityService = namedEntityService;
    }

    @GetMapping(value = {"/{page}", "/home/{page}"})
    private String findAll(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to,
            @RequestParam(required = false) SourceApi sourceApi,
            @RequestParam(required = false) String sentiment,
            @RequestParam(required = false) List<String> entityLabels,
            @RequestParam(required = false) String specificEntityLabel,
            @RequestParam(required = false) String specificEntityWord,
            @PathVariable Integer page,
            HttpServletRequest request,
            Model model
    ) {
        EntityFilterDto entityFilterDto = null;

        if (specificEntityLabel != null && specificEntityWord != null && (!specificEntityLabel.equals("") || !specificEntityWord.equals("")) ) {
            entityFilterDto = new EntityFilterDto(specificEntityLabel, specificEntityWord);
        }

        FilterArticleDto filterArticleDto = articleService.findAll(from, to, sourceApi, sentiment, entityLabels, entityFilterDto, page);

        Page<Article> articles = filterArticleDto.getArticlePage();

        String pieChartData = filterArticleDto.getPieChartData();

        String queryParams = request.getQueryString() != null ? request.getQueryString() : "";

        model.addAttribute("articles", articles);
        model.addAttribute("pieChartData", pieChartData);
        model.addAttribute("namedEntities", namedEntityService.getAllEntities());
        model.addAttribute("queryParams", queryParams);

        return "home";
    }

    @GetMapping("/onearticle/{id}")
    private String findOne(@PathVariable Long id,Model model){
        articleService.findById(id).ifPresent(o -> model.addAttribute("article",o));
        return "oneArticle.html";
    }
}
