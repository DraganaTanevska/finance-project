package mk.ukim.finki.web.financeproject.service;

import mk.ukim.finki.web.financeproject.model.Article;
import mk.ukim.finki.web.financeproject.model.dto.EntityFilterDto;
import mk.ukim.finki.web.financeproject.model.dto.FilterArticleDto;
import mk.ukim.finki.web.financeproject.model.enumerations.SourceApi;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ArticleService {
    FilterArticleDto findAll(Date from, Date to, SourceApi sourceApi, String sentiment, List<String> entityLabels, EntityFilterDto entityFilterDto, int page);
    Optional<Article> findById(Long id);
}
