package pl.getinpoland.model.article;

import pl.getinpoland.model.user.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Article {

    @Id
    @Column(columnDefinition = "INT(7) UNSIGNED", name = "id")
    @GeneratedValue
    private Long articleId;
    @Column(name = "image")
    private String articleImageLink;
    @Column(name = "title")
    private String articleTitle;
    @Lob
    @Column(name = "content")
    private String articleContent;
    @Column(name = "approval")
    private Long articleApproval;
    @Column(name = "date")
    private Timestamp articleDate;
    @Column(name = "author_id")
    private Long articleAuthor;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "approvedText")
    private List<User> approvingUsers;



    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
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

    public Long getArticleApproval() {
        return articleApproval;
    }

    public void setArticleApproval(Long articleApproval) {
        this.articleApproval = articleApproval;
    }

    public Timestamp getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(Timestamp articleDate) {
        this.articleDate = articleDate;
    }

    public Long getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(Long articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public List<User> getApprovingUsers() {
        return approvingUsers;
    }

    public void setApprovingUsers(List<User> approvingUsers) {
        this.approvingUsers = approvingUsers;
    }


}
