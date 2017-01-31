package edu.tstc.yy.dao;

import edu.tstc.yy.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * Created by w_2 on 2016-09-06.
 * 该映射接口用于对usertable表中的数据进行操作
 */


public interface UserDao {
    /**
     * 向数据库中添加用户，用于用户注册
     * @param user
     * @return
     */
    public Boolean addUser(User user);


    /**
     *  检查是否存在同名用户
     * @param whatName  用于设定要检查的同名类型
     * @param name 检查的数据
     * @return
     */
    public Boolean isSameName(@Param("whatName") String whatName,@Param("name") String name);

    /**
     * 用户登录验证
     * @param user 用户提交的信息
     * @return 返回数据库中存在的对应用户的信息，若不存在，返回null
     */
    public User userLoginDao(User user);

    /**
     * 验证用户密码是否正确，用于用户修改密码功能
     * @param user 用户提交的信息
     * @return 返回1代表验证成功，0代表验证失败
     */
    public Integer isPassWordRight(User user);

    /**
     * 修改用户密码
     * @param user 用户提交的信息，包含用户名和原密码
     * @param newPassWord 用户提交的新密码
     * @return 0代表0行被修改，修改失败；1代表1行被修改，修改成功
     */
    public Integer alterPassWord(@Param("user") User user,@Param("newPassWord") String newPassWord);

    /**
     * 获取用户权限码
     * @param user 用户提交的信息，包含userId
     * @return 用户的权限码，0代表普通用户，1代表管理员，2代表系统管理员
     */
    public Integer getdAdminRole(User user);

    public String getUserToken(User user);
    public Boolean insertUserToken(User user);

}
