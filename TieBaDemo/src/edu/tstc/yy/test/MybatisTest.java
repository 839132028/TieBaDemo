package edu.tstc.yy.test;

import edu.tstc.yy.model.User;
import edu.tstc.yy.model.UserInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by w_2 on 2016-09-26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MybatisTest {
    @Resource(name = "sessionFactory")
    SqlSessionFactory factory;
    @Test
   public void  OneToOneSerchTest(){
        SqlSession sqlSession=factory.openSession();
        User user=new User();
        user.setUserId(7);
        String sql="edu.tstc.yy.dao.UserInfoDao.getUserInfo";
        UserInfo userInfo=sqlSession.selectOne(sql,user);
        System.out.println(userInfo);
   }
}
