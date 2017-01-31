package edu.tstc.yy.controller;

import com.alibaba.fastjson.JSONObject;
import edu.tstc.yy.ReturnCode;
import edu.tstc.yy.model.User;
import edu.tstc.yy.model.UserInfo;
import edu.tstc.yy.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by w_2 on 2016-09-28.
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @ResponseBody
    @RequestMapping(value = "/addUserInfo.do", produces = "application/json")
    public JSONObject addUserInfo(@Valid UserInfo userInfo, BindingResult bindingResult) {
        JSONObject jsonObject = new JSONObject();
        Boolean flag = false;
        if (bindingResult.hasErrors()) {                        //数据验证失败，存在空值
            String failMessage = bindingResult.getFieldError().getDefaultMessage();
            if (failMessage.equals(ReturnCode.USERINFO_SEXCODE_NULL) || failMessage.equals(ReturnCode.USERINFO_SEXCODE_ERROR)) {
                jsonObject.put("ReturnCode", failMessage);
            } else {
                jsonObject.put("ReturnCode", ReturnCode.USERINFO_INSERT_FAILURE);
            }
        } else {
            try {
                flag = userInfoService.addUserInfo(userInfo);
                if (flag) {
                    jsonObject.put("ReturnCode", ReturnCode.USERINFO_INSERT_SUCCESS);
                    jsonObject.put("UserInfo", userInfoService.getUserInfo(userInfo.getUser()));
                } else {
                    jsonObject.put("ReturnCode", ReturnCode.USERINFO_INSERT_FAILURE);
                }
            } catch (DataIntegrityViolationException e) {
                jsonObject.clear();
                jsonObject.put("ReturnCode", ReturnCode.USERINFO_INSERT_FAILURE);
                return jsonObject;
            }

        }
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/getUserInfo.do", produces = "application/json")
    public JSONObject getUserInfo(User user) {
        JSONObject jsonObject = new JSONObject();
        UserInfo userInfo = userInfoService.getUserInfo(user);
        if (userInfo != null) {
            jsonObject.put("ReturnCode", ReturnCode.USERINFO_GETINFO_SUCCESS);
            jsonObject.put("UserInfo", userInfo);
        } else jsonObject.put("ReturnCode", ReturnCode.USERINFO_GETINFO_FAILURE);

        return jsonObject;
    }


}
