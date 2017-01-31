package edu.tstc.yy.serviceImp;

import edu.tstc.yy.dao.CommentDao;
import edu.tstc.yy.exception.NotAuthorException;
import edu.tstc.yy.exception.UserRoleValidated;
import edu.tstc.yy.model.Article;
import edu.tstc.yy.model.Comment;
import edu.tstc.yy.model.User;
import edu.tstc.yy.service.CommentService;
import edu.tstc.yy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by w_2 on 2016-10-18.
 */
@Service("commentService")
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImp implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserService userService;
    @Override
    public Boolean insertComment(Comment comment) {
        return commentDao.insertComment(comment);
    }

    @Override
    public ArrayList<Comment> moreComment(Article article, int startIndex, int limitNum) {
        return commentDao.moreComment(article,startIndex,limitNum);
    }

    @Override
    public Boolean delectComment(Comment comment , User user) throws UserRoleValidated {
        Comment authorVerifyComment=commentDao.findIDsByComment(comment);
        int getUserId=user.getUserId();
        int commentUserId=authorVerifyComment.getUser().getUserId();
        int articleUserId=authorVerifyComment.getArticle().getUser().getUserId();
        if (getUserId==commentUserId||getUserId==articleUserId){
            return commentDao.deleteComment(comment);
        }else if (userService.ValidatedAdminRole(user)){
            return commentDao.deleteComment(comment);
        }else return false;
    }

    @Override
    public Comment findIDsByComment(Comment comment) {
        return commentDao.findIDsByComment(comment);
    }

    @Override
    public Boolean editComment(Comment oldComment, Comment newComment, User user) throws NotAuthorException {
        int commentUser=commentDao.findUserByCommentId(oldComment);
        int userId=user.getUserId();
        if (commentUser==userId){
            return commentDao.editComment(oldComment,newComment);
        }else throw new NotAuthorException();
    }
}
