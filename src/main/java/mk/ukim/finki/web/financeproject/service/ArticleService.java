package mk.ukim.finki.web.financeproject.service;

import mk.ukim.finki.web.financeproject.model.Article;
import mk.ukim.finki.web.financeproject.model.Source;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> findAll(Date from, Date to, Source source);
    Optional<Article> findById(Long id);
}
