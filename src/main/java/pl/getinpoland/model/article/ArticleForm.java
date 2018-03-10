package pl.getinpoland.model.article;


import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ArticleForm {

    private String articleImageLink;
    @NotNull
    @Size(min = 3, max = 150)
    private String articleTitle;
    @Lob
    private String articleContent;

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

    public ArticleForm(String articleImageLink, String articleTitle, String articleContent) {
        this.articleImageLink = articleImageLink;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
    }

    public ArticleForm(){}
}
