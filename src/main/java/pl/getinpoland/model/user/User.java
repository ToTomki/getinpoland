package pl.getinpoland.model.user;

import pl.getinpoland.model.article.Article;
import pl.getinpoland.model.user.enums.UserRole;
import pl.getinpoland.model.user.enums.UserSex;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @Column(columnDefinition = "INT(8) UNSIGNED", name = "id")
    @GeneratedValue
    private long userId;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="sex")
    @Enumerated(EnumType.STRING)
    private UserSex userSex;
    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private UserRole userRole;
    @Column(name="user_description")
    private String userDescription; //user descriptions will be transferred to another table in the future
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="approved_text")
    private List<Article> approvedText;

    public User(UserForm userForm){
        this.username = userForm.getUsername();
        this.password = userForm.getPassword();
        this.userSex = userForm.getUserSex();
        this.userRole = userForm.getUserRole();
    }

    public User (){}

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserSex getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSex userSex) {
        this.userSex = userSex;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public List<Article> getApprovedText() {
        return approvedText;
    }

    public void setApprovedText(List<Article> approvedText) {
        this.approvedText = approvedText;
    }


}
