package edu.tstc.yy.model;

import edu.tstc.yy.ReturnCode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by w_2 on 2016-09-06.
 */
public class UserInfo implements Serializable {
    int userInfoId;
    @NotNull(message = ReturnCode.USERINFO_SEXCODE_NULL)
    @Max(value = 2,message = ReturnCode.USERINFO_SEXCODE_ERROR)
    @Min(value = 0,message = ReturnCode.USERINFO_SEXCODE_ERROR)
    Integer sex;
    String email;
    int userClass;
    User user;

    public UserInfo() {
    }

    public UserInfo( int sex, String email, int userClass, User user) {

        this.sex = sex;
        this.email = email;
        this.userClass = userClass;
        this.user = user;
    }

    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserClass() {
        return userClass;
    }

    public void setUserClass(int userClass) {
        this.userClass = userClass;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userInfoId=" + userInfoId +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", userClass=" + userClass +
                ", user=" + user +
                '}';
    }
}
