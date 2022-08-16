package mk.ukim.finki.web.financeproject.service;

import mk.ukim.finki.web.financeproject.model.Article;
import mk.ukim.finki.web.financeproject.model.dto.SpecificEntity;
import mk.ukim.finki.web.financeproject.model.enumerations.SourceApi;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ArticleService {
    Page<Article> findAll(Date from, Date to, SourceApi sourceApi, String sentiment, List<String> entityLabels, SpecificEntity specificEntity, int page);
    Optional<Article> findById(Long id);
}
