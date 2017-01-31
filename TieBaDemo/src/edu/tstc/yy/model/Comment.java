package edu.tstc.yy.model;

import edu.tstc.yy.group.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by w_2 on 2016-10-18.
 */
public class Comment {
    @NotNull(groups = { First.class,Second.class, Fourth.class},message = "commentId is null")
    private int commentId;
    @NotEmpty(groups = {First.class,Third.class, FIfth.class})
    private String commentDetails;
    private int isCommentDisplay;
    private Date commentCreatTime;
    @Valid
    private User user;
    @Valid
    private Article article;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentDetails() {
        return commentDetails;
    }

    public void setCommentDetails(String commentDetails) {
        this.commentDetails = commentDetails;
    }

    public int getIsCommentDisplay() {
        return isCommentDisplay;
    }

    public void setIsCommentDisplay(int isCommentDisplay) {
        this.isCommentDisplay = isCommentDisplay;
    }

    public Date getCommentCreatTime() {
        return commentCreatTime;
    }

    public void setCommentCreatTime(Date commentCreatTime) {
        this.commentCreatTime = commentCreatTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentDetails='" + commentDetails + '\'' +
                ", isCommentDisplay=" + isCommentDisplay +
                ", commentCreatTime=" + commentCreatTime +
                ", user=" + user +
                '}';
    }
}
