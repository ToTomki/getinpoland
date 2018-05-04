package pl.getinpoland.model.article;

import pl.getinpoland.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Article implements Serializable {

    @Id
    @Column(columnDefinition = "INT(7) UNSIGNED", name = "id")
    @GeneratedValue
    private long articleId;
    @Column(name = "image")
    private String articleImageLink;
    @Column(name = "title")
    private String articleTitle;
    @Lob
    @Column(name = "content")
    private String articleContent;
    @Column(name = "approval")
    private long articleApproval;
    @Column(name = "date")
    private Timestamp articleDate;
    @Column(name = "category")
    private int articleCategory;
    @Column(name = "author_id")
    private long articleAuthor;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "approvedText")
    private List<User> approvingUsers;


    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

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

    public long getArticleApproval() {
        return articleApproval;
    }

    public void setArticleApproval(long articleApproval) {
        this.articleApproval = articleApproval;
    }

    public Timestamp getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(Timestamp articleDate) {
        this.articleDate = articleDate;
    }

    public long getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(long articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public int getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(int articleCategory) {
        this.articleCategory = articleCategory;
    }

    public List<User> getApprovingUsers() {
        return approvingUsers;
    }

    public void setApprovingUsers(List<User> approvingUsers) {
        this.approvingUsers = approvingUsers;
    }

    public Article(ArticleForm articleForm){
        this.articleImageLink = articleForm.getArticleImageLink();
        this.articleTitle = articleForm.getArticleTitle();
        this.articleContent = articleForm.getArticleContent();
        this.articleDate = Timestamp.valueOf(LocalDateTime.now());
        this.articleCategory = articleForm.getArticleCategory();
        this.articleApproval = 0;
    }

public Article(){}

}
