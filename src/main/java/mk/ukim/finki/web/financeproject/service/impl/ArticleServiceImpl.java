package mk.ukim.finki.web.financeproject.service.impl;

import mk.ukim.finki.web.financeproject.model.Article;
import mk.ukim.finki.web.financeproject.model.enumerations.Source;
import mk.ukim.finki.web.financeproject.model.exceptions.ArticleNotFoundException;
import mk.ukim.finki.web.financeproject.repository.ArticleRepository;
import mk.ukim.finki.web.financeproject.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> findAll(Date from, Date to, Source source) {
        if(from==null&&to==null&&source==null)
        return articleRepository.findAll();
        else if(source!=null) {
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

    @Override
    public Page<Article> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Article> list;
        List<Article> articles = articleRepository.findAll();

        if (articles.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, articles.size());
            list = articles.subList(startItem, toIndex);
        }

        Page<Article> Article = new PageImpl<Article>(list, PageRequest.of(currentPage, pageSize), articles.size());

        return Article;
    }


}
