package edu.tstc.yy.controller;

import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import edu.tstc.yy.MyCustomDateEditor;
import edu.tstc.yy.ReturnCode;
import edu.tstc.yy.exception.NotAuthorException;
import edu.tstc.yy.exception.UserRoleValidated;
import edu.tstc.yy.group.First;
import edu.tstc.yy.group.Second;
import edu.tstc.yy.model.Article;
import edu.tstc.yy.model.User;
import edu.tstc.yy.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by w_2 on 2016-10-16.
 */

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/selectArticle.do")
    @ResponseBody
    public JSONObject selectArticle(int startIndex,int limitNum){
        JSONObject jsonObject=new JSONObject();
        try {
            ArrayList<Article> articles=articleService.selectArticle(startIndex, limitNum);
            if (articles==null){
                jsonObject.put("returnCode", ReturnCode.ARTICLE_SELECT_FAILURE);
                return jsonObject;
            }
            jsonObject.put("returnCode", ReturnCode.ARTICLE_SELECT_SUCCESS);
            jsonObject.put("articleNum",articles.size());
            for (int i=0;i<articles.size();i++){
                String keyName="article"+(i+1);
                jsonObject.put(keyName,articles.get(i));
            }
        }catch (DataIntegrityViolationException e){
            jsonObject.clear();
            jsonObject.put("ReturnCode",ReturnCode.NULLVALUE);
        }
        return jsonObject;
    }

    @InitBinder("articleCreatTime")
    public void initDate(WebDataBinder webDataBinder){

       // DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss z");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z", Locale.CHINA);
        df.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        webDataBinder.registerCustomEditor(Date.class, new MyCustomDateEditor(df, true));

    }
    @RequestMapping("/selectNextPageArticle.do")
    @ResponseBody
    public JSONObject selectNextPageArticle(int articleId, @ModelAttribute("articleCreatTime") Date articleCreatTime ,int limitNum){
        JSONObject jsonObject=new JSONObject();
        try {
            ArrayList<Article> articles=articleService.selectNextPageArticle(articleId,articleCreatTime,limitNum);
            if (articles==null){
                jsonObject.put("returnCode", ReturnCode.ARTICLE_SELECT_FAILURE);
                return jsonObject;
            }
            jsonObject.put("returnCode", ReturnCode.ARTICLE_SELECT_SUCCESS);
            jsonObject.put("articleNum",articles.size());
            for (int i=0;i<articles.size();i++){
                String keyName="article"+(i+1);
                jsonObject.put(keyName,articles.get(i));
            }
        }catch (DataIntegrityViolationException e){
            jsonObject.clear();
            jsonObject.put("ReturnCode",ReturnCode.NULLVALUE);
        }
        return jsonObject;

    }

    @RequestMapping("/articleDetail.do")
    @ResponseBody
    public JSONObject articleDetail(@Validated({First.class}) Article article, BindingResult bindingResult,int startIndex, int limitNum){
        JSONObject jsonObject=new JSONObject();
        if (bindingResult.hasErrors()){
            jsonObject.put("returnCode",ReturnCode.ERROR);
            jsonObject.put("message",bindingResult.getFieldError().getDefaultMessage());
        }else {
            Article returnArticle=articleService.articleDetail(article,startIndex,limitNum);
            if (returnArticle!=null){
                jsonObject.put("returnCode",ReturnCode.ARTICLE_SELECT_DETAIL_SUCCESS);
                jsonObject.put("articleDetail",returnArticle);
            }else {
                returnArticle=articleService.selectArticleByArticleId(article);
                if (returnArticle!=null){
                    jsonObject.put("returnCode",ReturnCode.ARTICLE_SELECT_DETAIL_SUCCESS);
                    jsonObject.put("articleDetail",returnArticle);
                }else {
                    jsonObject.put("returnCode",ReturnCode.ARTICLE_SELECT_DETAIL_FAILURE);
                }
            }
        }
        return jsonObject;
    }

    @RequestMapping("/selectArticleByUser.do")
    @ResponseBody
    public JSONObject selectArticleByUser(@Validated({Second.class}) User user, BindingResult bindingResult,int limitNum){
        JSONObject jsonObject=new JSONObject();
        if (bindingResult.hasErrors()){
            jsonObject.put("returnCode",ReturnCode.ERROR);
            jsonObject.put("message",bindingResult.getFieldError().getDefaultMessage());
        }else {
            ArrayList<Article> articles=articleService.selectArticleByUser(user,limitNum);
            if (articles==null){
                jsonObject.put("returnCode",ReturnCode.ARTICLE_SELECT_BY_USER_FAILURE);
            }else {
                jsonObject.put("returnCode",ReturnCode.ARTICLE_SELECT_BY_USER_ARTICLE_SELECT_SUCCESS);
                jsonObject.put("articleNum",articles.size());
                for (int i=0;i<articles.size();i++){
                    jsonObject.put("article"+(i+1),articles.get(i));
                }
            }
        }
        return jsonObject;

    }

    @RequestMapping("/selectMoreArticleByUser.do")
    @ResponseBody
    public JSONObject selectMoreArticleByUser(@Validated({Second.class}) User user, BindingResult bindingResult,Article article,int limitNum){
        JSONObject jsonObject=new JSONObject();
        if (bindingResult.hasErrors()){
            jsonObject.put("returnCode",ReturnCode.ERROR);
            jsonObject.put("message",bindingResult.getFieldError().getDefaultMessage());
        }else {
            ArrayList<Article> articles=articleService.selectMoreArticleByUser(user,article,limitNum);
            if (articles==null){
                jsonObject.put("returnCode",ReturnCode.ARTICLE_SELECT_BY_USER_FAILURE);
            }else {
                jsonObject.put("returnCode",ReturnCode.ARTICLE_SELECT_BY_USER_ARTICLE_SELECT_SUCCESS);
                jsonObject.put("articleNum",articles.size());
                for (int i=0;i<articles.size();i++){
                    jsonObject.put("article"+(i+1),articles.get(i));
                }
            }
        }
        return jsonObject;
    }

    @RequestMapping("/insertArticle.do")
    @ResponseBody
    public JSONObject insertArticle(@Validated(Second.class) Article article){
        JSONObject jsonObject=new JSONObject();
        try {
            Boolean flag=false;
            flag=articleService.insertArticle(article);
            if (flag){
                jsonObject.put("returnCode",ReturnCode.ARTICLE_INSERT_SUCCESS);
            }else jsonObject.put("returnCode",ReturnCode.ARTICLE_INSERT_FAILURE);

        }catch (DataIntegrityViolationException e){
            if (e.contains(MySQLIntegrityConstraintViolationException.class)){
                jsonObject.put("returnCode",ReturnCode.ARTICLE_INSERT_FAILURE_ILLICIT_USERID);
            }
        }finally {
            return jsonObject;
        }

    }

    @RequestMapping("/deleteArticle.do")
    @ResponseBody
    public JSONObject deleteArticle(Article article, User user){
        JSONObject jsonObject=new JSONObject();
        try {
            if (articleService.deleteArticle(user,article)){
                jsonObject.put("returnCode",ReturnCode.ARTICLE_DELETE_SUCCESS);
            }else {
                jsonObject.put("returnCode",ReturnCode.ARTICLE_DELETE_FAILURE);
            }
            
        } catch (UserRoleValidated userRoleValidated) {
            jsonObject.put("returnCode",ReturnCode.USER_ROLE_ERROR);
            jsonObject.put("message","ERROR ROLE");
        }finally {
            return jsonObject;
        }

    }


    @InitBinder("oldArticle")
    public void initOldArticle(WebDataBinder webDataBinder){
        webDataBinder.setFieldDefaultPrefix("oldArticle.");
    }
    @InitBinder("newArticle")
    public void initNewArticle(WebDataBinder webDataBinder){
        webDataBinder.setFieldDefaultPrefix("newArticle.");
    }

    @RequestMapping("/editArticle.do")
    @ResponseBody
    public JSONObject editArticle(@ModelAttribute("oldArticle") Article oldArticle,@ModelAttribute("newArticle") Article newArticle, User user){
        JSONObject jsonObject=new JSONObject();
        try {
            if (articleService.editArticle(oldArticle,newArticle,user)){
                jsonObject.put("returnCode",ReturnCode.ARTICLE_EDIT_SUCCESS);
            }else jsonObject.put("returnCode",ReturnCode.ARTICLE_EDIT_FAILURE);
        } catch (NotAuthorException e) {
            jsonObject.put("returnCode",ReturnCode.USER_NOT_AUTHOR);
            jsonObject.put("message","This user isn't the article's author");
            return jsonObject;
        }
        return jsonObject;
    }
}
