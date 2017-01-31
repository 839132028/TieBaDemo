package edu.tstc.yy.test;

import edu.tstc.yy.dao.CommentDao;
import edu.tstc.yy.model.Article;
import edu.tstc.yy.model.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by w_2 on 2016-10-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CommentDaoTest {
    @Autowired
    CommentDao commentDao;

    @Test
    public void moreCommentTest(){
        Article article=new Article();
        article.setArticleId(1);
        List<Comment> comments=commentDao.moreComment(article,0,100);
        comments.forEach(System.out::println);
    }
    @Test
    public void findCommentByIdTest(){
        Comment comment=new Comment();
        comment.setCommentId(1);
        System.out.println(commentDao.findIDsByComment(comment));
        System.out.println(commentDao.findIDsByComment(comment).getArticle());
    }
}
