package edu.tstc.yy.test;

import com.alibaba.fastjson.JSON;
import edu.tstc.yy.dao.UserDao;
import edu.tstc.yy.dao.UserInfoDao;
import edu.tstc.yy.model.User;
import edu.tstc.yy.model.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by w_2 on 2016-09-06.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DaoTest {
    static private User user=new User();
    static private UserInfo userInfo=new UserInfo();
    public static void initUser(){
        user.setUserName("tom@123456.com");
        user.setPassWord("123456");
        user.setNickName("to2m");
        user.setUserRoleId(1);
        user.setUserCreatTime(new Date());
        user.setUserIcon("awgwaerawedawfwaedwawaf");
    }
    public static void initUserInfo(){
        initUser();
        userInfo.setUser(user);
        userInfo.setSex(1);
        userInfo.setEmail("123456@qq.com");
        userInfo.setUserClass(101101);
    }
    @Autowired
    UserDao userDao;
    @Autowired
    UserInfoDao UserInfoDao;

    @Test
    public void addUserTest(){
        initUser();
        System.out.print(userDao.addUser(user));
    }

    @Test
    public void sameNameTest(){
        String whatName="nickName";
        String name="tom";
        if (userDao.isSameName(whatName,name)!=null){
            System.out.println(userDao.isSameName(whatName,name));
        }else System.out.println(false);
    }

    @Test
    public void isPassWordRightTest(){
        initUser();
        int i=userDao.isPassWordRight(user);
        System.out.println(i);
    }

    @Test
    public void alterPassWord(){
        initUser();
        System.out.println(userDao.alterPassWord(user,"8888888"));
    }

    @Test
    public void userInfoTest(){
        initUserInfo();
        userInfo.setUserClass(110);
        User user1=userDao.userLoginDao(user);
        user.setUserId(user1.getUserId());

        System.out.println(UserInfoDao.upDateInfo(userInfo));
    }

    @Test
    public void getuserToken(){
        User user=new User();
        user.setUserName("123456789");
        System.out.println(userDao.getUserToken(user));
    }
    @Test
    public void setUserTokenTest(){
        User user=new User();
        user.setUserName("123456789");
        user.setUserToken("122");
        userDao.insertUserToken(user);
    }

    @Test
    public void TestJson(){
        String jsonString="[\"/demo/fileUpload/1479030122900test.jpg\"]";
        List<String> strings= JSON.parseObject(jsonString,List.class);
        System.out.println(strings.get(0));
    }

}
