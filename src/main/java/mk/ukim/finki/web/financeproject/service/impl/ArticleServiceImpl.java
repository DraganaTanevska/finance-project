package mk.ukim.finki.web.financeproject.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.BooleanBuilder;
import mk.ukim.finki.web.financeproject.model.Article;
import mk.ukim.finki.web.financeproject.model.NamedEntities;
import mk.ukim.finki.web.financeproject.model.QArticle;
import mk.ukim.finki.web.financeproject.model.QNamedEntities;
import mk.ukim.finki.web.financeproject.model.dto.EntityFilterDto;
import mk.ukim.finki.web.financeproject.model.enumerations.SourceApi;
import mk.ukim.finki.web.financeproject.model.exceptions.ArticleNotFoundException;
import mk.ukim.finki.web.financeproject.model.piechart.PieChart;
import mk.ukim.finki.web.financeproject.repository.ArticleRepository;
import mk.ukim.finki.web.financeproject.service.ArticleService;
import mk.ukim.finki.web.financeproject.service.NamedEntityService;
import mk.ukim.finki.web.financeproject.service.PieChartFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final NamedEntityService namedEntityService;

    public ArticleServiceImpl(ArticleRepository articleRepository, NamedEntityService namedEntityService) {
        this.articleRepository = articleRepository;
        this.namedEntityService = namedEntityService;
    }

    public List<Article> findAll(Date from, Date to, SourceApi sourceApi, String sentiment, List<String> entityLabels, EntityFilterDto entityFilterDto) {
        QArticle article = QArticle.article;
        BooleanBuilder filter = new BooleanBuilder();

        if (from != null) {
            filter.and(article.date.after(from));
        }

        if (to != null) {
            filter.and(article.date.before(to));
        }

        if (sourceApi != null) {
            filter.and(article.sourceApi.eq(sourceApi));
        }

        if (sentiment != null && !sentiment.equals("")) {
            filter.and(article.sentiment.eq(sentiment));
        }

        if (entityLabels != null) {
            for (String label : entityLabels) {
                filter.and(article.namedEntitiesList.any().label.eq(label));
            }
        }

        if (entityFilterDto != null) {
            QNamedEntities qNamedEntities = QNamedEntities.namedEntities;

            BooleanBuilder entityFilter = new BooleanBuilder();

            if (!entityFilterDto.getSpecificEntityLabel().isEmpty()) {
                entityFilter.and(qNamedEntities.label.eq(entityFilterDto.getSpecificEntityLabel()));
            }

            if (!entityFilterDto.getSpecificEntityWord().isEmpty()) {
                entityFilter.and(qNamedEntities.word.eq(entityFilterDto.getSpecificEntityWord()));
            }

            List<NamedEntities> entities = namedEntityService.getNamedEntities(entityFilter);

            filter.and(article.namedEntitiesList.any().in(entities));
        }

        return articleRepository.findAll(filter);
    }

    public String getJsonPieChartSentimentData(Integer positiveCount, Integer negativeCount, Integer neutralCount) {
        PieChart pieChart = PieChartFactory.createPieChartSentiment(positiveCount, negativeCount, neutralCount);

        ObjectMapper objectMapper = new ObjectMapper();

        String pieChartJson = null;

        try {
            pieChartJson = objectMapper.writeValueAsString(pieChart);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return pieChartJson;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.of(articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id)));
    }
}
