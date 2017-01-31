package edu.tstc.yy.model;

import edu.tstc.yy.group.First;
import edu.tstc.yy.group.Second;
import edu.tstc.yy.group.Third;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by w_2 on 2016-09-06.
 */
public class User implements Serializable {

    @NotNull(groups = {Second.class, Third.class})
    private int userId;
    @NotEmpty(groups = {First.class})
    private String userName;
    @NotEmpty(groups = {First.class})
    private String passWord;
    @NotEmpty(groups = {First.class})
    private String nickName;
    private String userIcon;
    private Date userCreatTime;
    private int userRoleId;

    private String userToken;

    public User() {
    }

    public User(int userId, String userName, String passWord, String nickName, String userIcon, Date userCreatTime, int userRoleId) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
        this.nickName = nickName;
        this.userIcon = userIcon;
        this.userCreatTime = userCreatTime;
        this.userRoleId = userRoleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public Date getUserCreatTime() {
        return userCreatTime;
    }

    public void setUserCreatTime(Date userCreatTime) {
        this.userCreatTime = userCreatTime;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", userCreatTime=" + userCreatTime +
                ", userRoleId=" + userRoleId +
                '}';
    }

}
