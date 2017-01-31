package edu.tstc.yy.test;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import edu.tstc.yy.dao.UserInfoDao;
import edu.tstc.yy.model.User;
import edu.tstc.yy.model.UserInfo;
import edu.tstc.yy.service.UserInfoService;
import edu.tstc.yy.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.Valid;

/**
 * Created by w_2 on 2016-09-06.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ServiceTest {
    private static User user=new User();
    @Autowired
    UserService userService;
    @Autowired
    UserInfoService userInfoService;

    @Test
    public void userRegisterTest() throws MySQLIntegrityConstraintViolationException {

        System.out.println(userService.userRegister(user));
    }

    @Test

    public void addUserInfoTest(){
        User user=new User();
        user.setUserId(15);
        UserInfo userInfo=new UserInfo(0,"123456@oo.com",123455,user);
        System.out.println(userInfoService.updateUserInfo( userInfo));
      /* System.out.println(userInfoService.getUserInfo(user));*/
    }


}
