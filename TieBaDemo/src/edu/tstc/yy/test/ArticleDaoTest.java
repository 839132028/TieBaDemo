package edu.tstc.yy.test;

import edu.tstc.yy.dao.ArticleDao;
import edu.tstc.yy.model.Article;
import edu.tstc.yy.model.Comment;
import edu.tstc.yy.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by w_2 on 2016-10-16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ArticleDaoTest {
    @Autowired
    ArticleDao articleDao;
    @Test
    public void insertArticleTest(){
        Article article=new Article();
        User user=new User();
        user.setUserId(17);
        article.setUser(user);
        article.setArticleHeadline("不带图片的");
        article.setArticleDetails("dassasaew");
        article.setIsArticleDisplay(1);

        System.out.println(articleDao.insertArticle(article));
    }

    @Test
    public void selectArticleTest(){
        ArrayList<Article> articles=articleDao.selectArticle(0,2);
        for (Article article:articles){
            System.out.println(article);
        }
    }

    @Test
    public void selectArticleByUserTest(){
        User user=new User();
        user.setUserId(17);
        ArrayList<Article> articles=articleDao.selectArticleByUserId(user,10);
        for (Article article:articles){
            System.out.println(article);
        }
    }


    @Test
    public void ArticleDetaileTest(){
        Article article=new Article();
        article.setArticleId(8);
        article=articleDao.articleDetail(article,0,100);
        List<Comment> comments=article.getComments();
        comments.forEach(System.out::println);
    }

    @Test
    public void EditArticleTest(){
        Article oldArticle=new Article();
        oldArticle.setArticleId(6);
        Article newArticle=new Article();
        newArticle.setArticleDetails("这是修改过的文章");
        System.out.println(articleDao.editArticle(oldArticle,newArticle));
    }




}
