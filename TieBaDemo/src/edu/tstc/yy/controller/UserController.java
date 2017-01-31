package edu.tstc.yy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import edu.tstc.yy.ReturnCode;
import edu.tstc.yy.model.User;
import edu.tstc.yy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by w_2 on 2016-09-06.
 * 接收前端传回的user相关数据并处理，返回json数据
 */


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/userRegister.do")
    public void userRegister(@Valid User user, BindingResult bindingResult, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("returnCode", ReturnCode.USER_REGISTER_FAILURE);
        Boolean sameName = userService.isNameSame("userName", user.getUserName());          //检查用户提交的用户名是否已存在
        response.setCharacterEncoding("UTF-8");
        if (sameName) {
            jsonObject.replace("retrunCode", ReturnCode.USER_REGISTER_SAMEUSERNAME);
            jsonObject.put("massage", "This userName is already exists");
            response.getWriter().print(jsonObject);
        } else {
            sameName = userService.isNameSame("nickName", user.getNickName());              //检查用户提交的昵称是否已经存在
            if (sameName) {
                jsonObject.replace("retrunCode", ReturnCode.USER_REGISTER_SAMENICKNAME);
                jsonObject.put("massage", "This nickName is already exists");
                response.getWriter().print(jsonObject);
            } else try {
                if (bindingResult.hasErrors()) {                                           //user数据验证失败，则提交数据存在空值
                    jsonObject.replace("retrunCode", ReturnCode.NULLVALUE);
                    jsonObject.put("massage", "存在空值");
                    response.getWriter().print(jsonObject);
                } else {
                    userService.userRegister(user);                                        //用户注册
                    jsonObject.replace("returnCode", ReturnCode.USER_REGISTER_SUCCESS);
                    jsonObject.put("massage", "注册成功");
                    response.getWriter().print(jsonObject);
                }
            } catch (DataIntegrityViolationException e) {
                jsonObject.replace("retrunCode", ReturnCode.USER_REGISTER_FAILURE);
                jsonObject.put("massage", "注册失败");
                response.getWriter().print(jsonObject);
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/isSameName.do")
    public void isSameName(String whatName, String Name, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = new JSONObject();
        if ((!Name .equals(""))&& userService.isNameSame(whatName, Name)) {
            jsonObject.put("returnCode", ReturnCode.USER_SAMENAME_TRUE);
        } else if (Name.equals("")) {
            jsonObject.put("returnCode", ReturnCode.NULLVALUE);
        } else {
            jsonObject.put("returnCode", ReturnCode.USER_SAMENAME_FALSE);
        }
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "/userLogin.do",produces = {"application/json;charset=UTF-8"})
    public void userLogin(User user, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = new JSONObject();
        response.setCharacterEncoding("UTF-8");
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";//格式化json中Date类型数据为yyyy-MM-dd HH:mm:ss

        if (userService.isNameSame("userName", user.getUserName())) {
            if (userService.getUserToken(user)!=null){
                //// TODO: 2016-11-05 集成信鸽推送，强制上一个用户下线
            }
            User getUser = userService.userLogin(user);
            if (getUser != null) {
                jsonObject.put("returnCode", ReturnCode.USER_LOGIN_SUCCESS);
                jsonObject.put("user", getUser);
            } else jsonObject.put("returnCode", ReturnCode.USER_LOGIN_FAILURE);

        } else {
            jsonObject.put("returnCode", ReturnCode.USER_LOGIN_NOUSERNAME);
        }
        response.getWriter().print(JSON.toJSONStringWithDateFormat(jsonObject, "yyyy-MM-dd HH:mm:ss"));
    }

    @ResponseBody
    @RequestMapping(value = "/alterPassWord.do",produces = "application/json")
    public JSONObject alterPassWord( User user, String newPassWord) throws IOException {
        JSONObject jsonObject = new JSONObject();
        try {
            if (userService.alterPassWord(user, newPassWord)) {
                jsonObject.put("returnCode", ReturnCode.USER_ALTERPASSWORD_SUCCESS);
            } else {
                jsonObject.put("returnCode", ReturnCode.USER_ALTERPASSWORD_FAILURE);
            }
        } catch (ConstraintViolationException e) {                  //新密码为空，数据验证失败，捕获验证异常
            Set<ConstraintViolation<?>> constraintViolations=e.getConstraintViolations();
            Iterator iterator=constraintViolations.iterator();
            ConstraintViolation violation= (ConstraintViolation) iterator.next();
            jsonObject.put("returnCode", ReturnCode.NULLVALUE);
            return jsonObject;
        }
        return jsonObject;
    }
}
