package edu.tstc.yy.serviceImp;

import edu.tstc.yy.dao.ArticleDao;
import edu.tstc.yy.dao.UserDao;
import edu.tstc.yy.exception.NotAuthorException;
import edu.tstc.yy.exception.UserRoleValidated;
import edu.tstc.yy.model.Article;
import edu.tstc.yy.model.User;
import edu.tstc.yy.service.ArticleService;
import edu.tstc.yy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.InlineList;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by w_2 on 2016-10-16.
 */

@Service("articleService")
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImp implements ArticleService {

    @Autowired
   private ArticleDao articleDao;
    @Autowired
    private UserService userService;

    @Override
    public ArrayList<Article> selectArticle(Integer startIndex, Integer limitNum) {
        return articleDao.selectArticle(startIndex, limitNum);
    }

    @Override
    public ArrayList<Article> selectNextPageArticle(Integer articleId, Date articleCreatTime, Integer limitNum) {
        return articleDao.selectNextPageArticle(articleId,articleCreatTime,limitNum);
    }

    @Override
    public ArrayList<Article> selectArticleByUser(User user,Integer limitNum) {
        return articleDao.selectArticleByUserId(user,limitNum);
    }

    @Override
    public ArrayList<Article> selectMoreArticleByUser(User user, Article article, Integer limitNum) {
        return articleDao.selectMoreActicleByUserId(user,article,limitNum);
    }

    @Override
    public Boolean deleteArticle(User user, Article article) throws UserRoleValidated {
        if (user.getUserId()==articleDao.findUserIdByArticle(article)){
            return articleDao.deleteArticle(article);
        }else if (userService.ValidatedAdminRole(user)){
           return articleDao.deleteArticle(article);
        }else return false;
    }

    @Override
    public Boolean insertArticle(Article article) {
        return articleDao.insertArticle(article);
    }

    @Override
    public Article articleDetail(Article article,int startIndex,int limitNum) {
        return articleDao.articleDetail(article,startIndex,limitNum);
    }

    @Override
    public Article selectArticleByArticleId(Article article) {
        return articleDao.selectArticleByArticleId(article);
    }

    @Override
    public Boolean editArticle(Article oldArticle, Article newArticle, User user) throws NotAuthorException {

        int articleUserId=articleDao.findUserIdByArticle(oldArticle);
        if (user.getUserId()==articleUserId){
            return articleDao.editArticle(oldArticle,newArticle);
        }else throw new NotAuthorException();
    }
}
