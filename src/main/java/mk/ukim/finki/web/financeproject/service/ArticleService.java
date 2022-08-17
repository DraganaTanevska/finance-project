package mk.ukim.finki.web.financeproject.service;

import mk.ukim.finki.web.financeproject.model.Article;
import mk.ukim.finki.web.financeproject.model.dto.EntityFilterDto;
import mk.ukim.finki.web.financeproject.model.enumerations.SourceApi;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ArticleService {
    List<Article> findAll(Date from, Date to, SourceApi sourceApi, String sentiment, List<String> entityLabels, EntityFilterDto entityFilterDto, int page);
    String getJsonPieChartSentimentData(Integer positiveCount, Integer negativeCount, Integer neutralCount);
    Optional<Article> findById(Long id);
}
