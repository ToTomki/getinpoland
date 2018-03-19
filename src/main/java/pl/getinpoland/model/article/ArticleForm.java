package pl.getinpoland.model.article;


import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.InputMismatchException;

public class ArticleForm {

    private String articleImageLink;
    @NotNull
    @Size(min = 3, max = 150)
    private String articleTitle;
    @Lob
    private String articleContent;
    private int articleCategory;

    public String getArticleImageLink() {
        return articleImageLink;
    }

    public void setArticleImageLink(String articleImageLink) {
        this.articleImageLink = articleImageLink;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public int getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(int articleCategory) {
        this.articleCategory = articleCategory;
    }

    public ArticleForm(String articleImageLink, String articleTitle, String articleContent, int articleCategory) {
        this.articleImageLink = articleImageLink;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        try {
            this.articleCategory = articleCategory;
        }
        catch (InputMismatchException e) { //todo There's need to verify if it is okay to handle the case when articleCategory is null
            this.articleCategory = 0;
        }
    }

    public ArticleForm(){}
}
