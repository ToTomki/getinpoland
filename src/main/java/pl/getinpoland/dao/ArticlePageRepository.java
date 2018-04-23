package pl.getinpoland.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import pl.getinpoland.model.article.Article;

import java.util.List;


public interface ArticlePageRepository extends CrudRepository<Article, Long> {
    Page<Article> findAll(Pageable pageable);
}
