package edu.tstc.yy.service;

import edu.tstc.yy.exception.NotAuthorException;
import edu.tstc.yy.exception.UserRoleValidated;
import edu.tstc.yy.model.Article;
import edu.tstc.yy.model.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by w_2 on 2016-10-16.
 */
public interface ArticleService {
    ArrayList<Article> selectArticle(Integer startIndex,Integer limitNum);
    ArrayList<Article> selectNextPageArticle(Integer articleId, Date articleCreatTime, Integer limitNum);
    ArrayList<Article> selectArticleByUser(User user,Integer limitNum);
    ArrayList<Article> selectMoreArticleByUser(User user,Article article,Integer limitNum);
    Boolean deleteArticle(User user,Article article) throws UserRoleValidated;
    Boolean insertArticle(Article article);
    Article articleDetail(Article article,int startIndex,int limitNum);
    Article selectArticleByArticleId(Article article);

    Boolean editArticle(Article oldArticle,Article newArticle,User user) throws NotAuthorException;
}
