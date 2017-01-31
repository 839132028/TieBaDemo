package edu.tstc.yy.controller;

import com.alibaba.fastjson.JSONObject;
import edu.tstc.yy.ReturnCode;
import edu.tstc.yy.exception.NotAuthorException;
import edu.tstc.yy.exception.UserRoleValidated;
import edu.tstc.yy.group.*;
import edu.tstc.yy.model.Article;
import edu.tstc.yy.model.Comment;
import edu.tstc.yy.model.User;
import edu.tstc.yy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by w_2 on 2016-10-18.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @RequestMapping("/insertComment.do")
    @ResponseBody
    public JSONObject insertComment(@Validated({Third.class}) Comment comment, BindingResult bindingResult){
        JSONObject jsonObject=new JSONObject();
        if (bindingResult.hasErrors()){
            jsonObject.put("returnCode", ReturnCode.ERROR);
            jsonObject.put("message",bindingResult.getFieldError().getDefaultMessage());
        }else {
            try {
                if (commentService.insertComment(comment)){
                    jsonObject.put("returnCode",ReturnCode.COMMENT_INSERT_SUCCESS);
                }else jsonObject.put("returnCode",ReturnCode.COMMENT_INSERT_FAILURE);
            }catch (DataIntegrityViolationException e){
                jsonObject.put("returnCode",ReturnCode.COMMENT_INSERT_FAILURE);
                return jsonObject;
            }
        }
        return jsonObject;
    }

    @RequestMapping("/moreComment.do")
    @ResponseBody
    public JSONObject moreComment(@Validated(First.class) Article article,BindingResult bindingResult,int startIndex, int limitNum){
        JSONObject jsonObject=new JSONObject();
        if (bindingResult.hasErrors()){
            jsonObject.put("returnCode",ReturnCode.ERROR);
            jsonObject.put("message",bindingResult.getFieldError().getDefaultMessage());
        }else {
            List<Comment> comments=commentService.moreComment(article,startIndex,limitNum);
            if(comments.size()==0){

                jsonObject.put("returnCode",ReturnCode.COMMENT_SELECTMORE_NOMORE);
            }else {
                jsonObject.put("returnCode",ReturnCode.COMMENT_SELECTMORE_SUCCESS);
                for (int i=0;i<comments.size();i++){
                    jsonObject.put("comment"+(i+1),comments.get(i));
                }
            }
        }
        return jsonObject;
    }

    @RequestMapping("/deleteComment.do")
    @ResponseBody
    public JSONObject deleteComment(@Validated(Second.class) Comment comment,BindingResult bindingResult2,@Validated(Second.class) User user,BindingResult bindingResult){
        JSONObject jsonObject=new JSONObject();
        if (bindingResult.hasErrors()||bindingResult2.hasErrors()){
            jsonObject.put("returnCode",ReturnCode.ERROR);
            if (bindingResult.hasErrors()){
                jsonObject.put("message",bindingResult.getFieldError().getDefaultMessage());
            }else jsonObject.put("message",bindingResult2.getFieldError().getDefaultMessage());
        }else {
            try {
                if (commentService.delectComment(comment,user)){
                    jsonObject.put("returnCode",ReturnCode.COMMENT_DELETE_SUCCESS);
                }else jsonObject.put("returnCode",ReturnCode.COMMENT_DELETE_FAILURE);
            } catch (UserRoleValidated userRoleValidated) {
                jsonObject.put("returnCode",ReturnCode.USER_ROLE_ERROR);
                jsonObject.put("message","ERROR ROLE");
                return jsonObject;
            }
        }
        return jsonObject;

    }


    @InitBinder("oldComment")
    public void initOldComment(WebDataBinder webDataBinder){
        webDataBinder.setFieldDefaultPrefix("oldComment.");
    }
    @InitBinder("newComment")
    public void initNewComment(WebDataBinder webDataBinder){
        webDataBinder.setFieldDefaultPrefix("newComment.");
    }
    @RequestMapping("/editComment.do")
    @ResponseBody
    public JSONObject editComment(@Validated(Fourth.class)@ModelAttribute("oldComment") Comment oldComment, BindingResult oldBindingResult, @Validated(FIfth.class)
            @ModelAttribute("newComment") Comment newComment , BindingResult newBindingResult, User user){
        JSONObject jsonObject=new JSONObject();
        if (oldBindingResult.hasErrors()||newBindingResult.hasErrors()){
            jsonObject.put("returnCode",ReturnCode.ERROR);
            if (oldBindingResult.hasErrors()){
                jsonObject.put("message",oldBindingResult.getFieldError().getDefaultMessage());
            }else jsonObject.put("message",newBindingResult.getFieldError().getDefaultMessage());
        }else {
            try {
                if (commentService.editComment(oldComment,newComment,user)){
                    jsonObject.put("returnCode",ReturnCode.COMMENT_EDIT_SUCCESS);
                }else jsonObject.put("returnCode",ReturnCode.COMMENT_EDIT_FAILURE);

            } catch (NotAuthorException e) {
                jsonObject.put("returnCode",ReturnCode.USER_NOT_AUTHOR);
                jsonObject.put("message","This user isn't the comment's author");
                return jsonObject;
            }
        }
        return jsonObject;
    }
}
