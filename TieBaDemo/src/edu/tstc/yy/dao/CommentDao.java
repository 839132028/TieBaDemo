package edu.tstc.yy.dao;

import edu.tstc.yy.model.Article;
import edu.tstc.yy.model.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * Created by w_2 on 2016-10-18.
 */
public interface CommentDao {
   public Boolean insertComment(Comment comment);
   public ArrayList<Comment> moreComment(@Param("article") Article article, @Param("startIndex") int startIndex, @Param("limitNum") int limitNum);
   public Boolean deleteComment(Comment comment);
   public Comment findIDsByComment(Comment comment);

   public int findUserByCommentId(Comment comment);
   public Boolean editComment(@Param("oldComment") Comment oldComment,@Param("newComment") Comment newComment);

}
