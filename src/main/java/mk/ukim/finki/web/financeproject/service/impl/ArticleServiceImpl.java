package mk.ukim.finki.web.financeproject.service.impl;

import mk.ukim.finki.web.financeproject.model.Article;
import mk.ukim.finki.web.financeproject.model.Source;
import mk.ukim.finki.web.financeproject.model.exceptions.ArticleNotFoundException;
import mk.ukim.finki.web.financeproject.model.exceptions.SourceNotFoundException;
import mk.ukim.finki.web.financeproject.repository.ArticleRepository;
import mk.ukim.finki.web.financeproject.repository.SourceRepository;
import mk.ukim.finki.web.financeproject.service.ArticleService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final SourceRepository sourceRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository, SourceRepository sourceRepository) {
        this.articleRepository = articleRepository;
        this.sourceRepository = sourceRepository;
    }

    public List<Article> findAll(Date from, Date to, Long id) {
        if(from==null&&to==null&&id==null)
        return articleRepository.findAll();
        else if(id!=null) {
            Source source = sourceRepository.findById(id).orElseThrow(() -> new SourceNotFoundException(id));
            if(from!=null&&to!=null) {
                return articleRepository.findArticlesByDateBetweenAndSource(from,to,source);
            }
            else if(from!=null) {
                return articleRepository.findArticlesByDateAfterAndSource(from,source);
            }
            else if(to!=null){
                return articleRepository.findArticlesByDateBeforeAndSource(to,source);
            }
            else
                return articleRepository.findArticlesBySource(source);
        }
        else if(from!=null){
            if(to!=null)
                return articleRepository.findArticlesByDateBetween(from,to);
            else
                return articleRepository.findArticlesByDateAfter(from);
        }
        else
            return articleRepository.findArticlesByDateBefore(to);

    }


    @Override
    public Optional<Article> findById(Long id) {
        return Optional.of(articleRepository.findById(id).orElseThrow(()->new ArticleNotFoundException(id)));
    }

}
