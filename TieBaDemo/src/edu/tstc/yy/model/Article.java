package edu.tstc.yy.model;

import edu.tstc.yy.group.First;
import edu.tstc.yy.group.Second;
import edu.tstc.yy.group.Third;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by w_2 on 2016-10-16.
 */
public class Article {
    @Valid
    private User user;
    @NotNull(groups = {First.class, Third.class})
    private int articleId;
    @NotEmpty(groups = {Second.class})
    private String articleHeadline;
    private String articleDetails;
    private Date articleCreatTime;
    private int isArticleDisplay;
    @NotNull(groups = {Second.class})
    private int articleImageNum;
    private String imageUrls;

    private List<Comment> comments;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleHeadline() {
        return articleHeadline;
    }

    public void setArticleHeadline(String articleHeadline) {
        this.articleHeadline = articleHeadline;
    }

    public String getArticleDetails() {
        return articleDetails;
    }

    public void setArticleDetails(String articleDetails) {
        this.articleDetails = articleDetails;
    }

    public Date getArticleCreatTime() {
        return articleCreatTime;
    }

    public void setArticleCreatTime(Date articleCreatTime) {
        this.articleCreatTime = articleCreatTime;
    }

    public int getIsArticleDisplay() {
        return isArticleDisplay;
    }

    public void setIsArticleDisplay(int isArticleDisplay) {
        this.isArticleDisplay = isArticleDisplay;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }





    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    public int getArticleImageNum() {
        return articleImageNum;
    }

    public void setArticleImageNum(int articleImageNum) {
        this.articleImageNum = articleImageNum;
    }

    @Override
    public String toString() {
        return "Article{" +
                "user=" + user +
                ", articleId=" + articleId +
                ", articleHeadline='" + articleHeadline + '\'' +
                ", articleDetails='" + articleDetails + '\'' +
                ", articleCreatTime=" + articleCreatTime +
                ", isArticleDisplay=" + isArticleDisplay +
                ", articleImageNum=" + articleImageNum +
                ", imageUrls='" + imageUrls + '\'' +
                ", comments=" + comments +
                '}';
    }
}
