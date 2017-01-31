package edu.tstc.yy.service;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import edu.tstc.yy.ReturnCode;
import edu.tstc.yy.exception.UserRoleValidated;
import edu.tstc.yy.model.User;
import edu.tstc.yy.model.UserInfo;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

/**
 * Created by w_2 on 2016-09-06.
 * 定义user模块功能
 */
@Validated
public interface UserService {
    //用户注册
    boolean userRegister(User user) ;
    //用户登录
    User userLogin(User user);
    //同名查询
    boolean isNameSame(String whatName,String name);
    //密码修改
    boolean alterPassWord(User user,@NotEmpty(message = ReturnCode.NULLVALUE) String newPassword);

    Boolean ValidatedAdminRole(User user) throws UserRoleValidated;

    String getUserToken(User user);

}
