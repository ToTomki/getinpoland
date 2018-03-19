package pl.getinpoland.model.article;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ArticleCategory {

    @Id
    @Column(name = "id")
    private int articleCategory;
    @Column(name = "eng_category_name")
    private String engCategoryName;

}
