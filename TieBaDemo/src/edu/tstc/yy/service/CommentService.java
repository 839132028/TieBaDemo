package edu.tstc.yy.service;

import edu.tstc.yy.exception.NotAuthorException;
import edu.tstc.yy.exception.UserRoleValidated;
import edu.tstc.yy.model.Article;
import edu.tstc.yy.model.Comment;
import edu.tstc.yy.model.User;

import java.util.ArrayList;

/**
 * Created by w_2 on 2016-10-18.
 */
public interface CommentService {
    Boolean insertComment(Comment comment);
    ArrayList<Comment> moreComment(Article article,int startIndex,int limitNum);
    Boolean delectComment(Comment comment, User user) throws UserRoleValidated;
    Comment findIDsByComment(Comment comment);
    Boolean editComment(Comment oldComment,Comment newComment,User user) throws NotAuthorException;
}
