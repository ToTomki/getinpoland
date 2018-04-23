package pl.getinpoland.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.getinpoland.model.article.Article;

import java.util.List;


public interface ArticleRepository extends CrudRepository<Article, Long> {
    Page<Article> findAllByOrderByArticleDateDesc(Pageable pageable);
    List<Article> findLast7ByOrderByArticleIdDesc();
}
