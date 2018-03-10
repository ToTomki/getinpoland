package pl.getinpoland.model.user;

import pl.getinpoland.model.user.enums.UserRole;
import pl.getinpoland.model.user.enums.UserSex;

public class UserForm {

    private String username;
    private String password;
    private UserSex userSex;
    private UserRole userRole;


    public UserForm(){};

    private UserForm(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.userSex = builder.userSex;
        this.userRole = builder.userRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = this.username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class Builder{
        private String username;
        private String password;
        private UserSex userSex;
        private UserRole userRole;

        public Builder (String username, String password, UserSex userSex, UserRole userRole) {
            this.username = username;
            this.password = password;
            this.userSex = userSex;
            this.userRole = userRole;
        }

        public UserForm build() {return new UserForm(this);}
    }


    @Override
    public String toString() {
        return "UserForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userSex=" + userSex +
                ", userRole=" + userRole +
                '}';
    }


}
