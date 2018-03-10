package pl.getinpoland.dao;

import org.springframework.data.repository.CrudRepository;
import pl.getinpoland.model.article.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
