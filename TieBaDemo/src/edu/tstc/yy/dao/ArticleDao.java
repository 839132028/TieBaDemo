package edu.tstc.yy.dao;

import edu.tstc.yy.model.Article;
import edu.tstc.yy.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by w_2 on 2016-10-16.
 */
public interface ArticleDao {
    /**
     * 向articletable中插入一篇文章
     * @param article 用户提交的文章
     * @return 成功返回true；失败返回false
     */
    public boolean insertArticle(Article article);

    /**
     * 获得articletable中一定数量的文章，以ArrayList形式储存
     * @param startIndex 限制第一条文章信息在数据库中的位置
     * @param limitNum 获得文章信息的条数
     * @return 以ArrayList形式储存的limitNum条Article信息
     */
    public ArrayList<Article> selectArticle(@Param("startIndex") Integer startIndex, @Param("limitNum") Integer limitNum);

    ArrayList<Article> selectNextPageArticle(@Param("articleId")Integer articleId,@Param("articleCreatTime")Date articleCreatTime,@Param("limitNum") Integer limitNum);

    /**
     * 将一篇文章的isArticleDdisplay属性置为0，使文章不可被查询，即对外部显示为删除文章
     * @param article 包含所要删除文章的articleId
     * @return 成功返回true；失败返回false
     */
    public boolean deleteArticle(Article article);

    /**
     * 查询指定用户所发表的文章
     * @param user 包含所要查询的用户的userId
     * @return 以ArrayList形式储存的Article信息
     */
    public ArrayList<Article> selectArticleByUserId(@Param("user") User user,@Param("limitNum") Integer limitNum);


    public ArrayList<Article> selectMoreActicleByUserId(@Param("user")User user,@Param("article")Article article,@Param("limitNum")Integer limitNum);

    /**
     * 按照articleId查找userId
     * @param article 包含所要查找的文章的articleId
     * @return 在数据库中查找的userId
     */
    public int findUserIdByArticle(Article article);

    /**
     * 编辑已经上传的文章
     * @param oldArticle
     * @param newArticle
     * @return
     */
    public Boolean editArticle(@Param("oldArticle") Article oldArticle,@Param("newArticle") Article newArticle);

    /**
     * 获取文章内容以及该文章limitNum个评论内容
     * @param article 包含所要查询的articleId
     * @param startIndex
     * @param limitNum
     * @return
     */
    public Article articleDetail(@Param("article") Article article,@Param("startIndex") int startIndex,@Param("limitNum") int limitNum);
    public Article selectArticleByArticleId(Article article);

}
