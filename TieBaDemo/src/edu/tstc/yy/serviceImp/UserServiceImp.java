package edu.tstc.yy.serviceImp;

import edu.tstc.yy.ReturnCode;
import edu.tstc.yy.dao.UserDao;
import edu.tstc.yy.exception.UserRoleValidated;
import edu.tstc.yy.model.User;
import edu.tstc.yy.service.UserService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * Created by w_2 on 2016-09-06.
 * 实现与user模块相关功能
 */

@Validated
@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean userRegister(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User userLogin(User user) {
        User getUser=userDao.userLoginDao(user);
        return getUser;
    }

    @Override
    public boolean isNameSame(String whatName, String name) {
        Boolean flag=userDao.isSameName(whatName, name);
        if (flag!=null){
            return true;
        }else return false;
    }

    @Override
    public boolean alterPassWord(User user, @NotEmpty(message = ReturnCode.NULLVALUE) String newPassword)   {
        boolean flag=false;
        int n=userDao.alterPassWord(user,newPassword);
        if (n!=0){
            flag=true;
        }
        return flag;
    }

    /**
     * 验证用户权限角色
     * @param user 包含用户所提交的userId
     * @return ture表示该用户为管理员身份或系统管理员身份
     * @throws UserRoleValidated 抛出该异常说明该用户为普通用户身份或者获得的权限码异常
     */

    @Override
    public Boolean ValidatedAdminRole(User user) throws UserRoleValidated {
        boolean flag=false;
        int getRoleId=userDao.getdAdminRole(user);
        if (getRoleId<=0||getRoleId>2){
            throw new UserRoleValidated("用户身份有误");
        }else {
            flag=true;
            return flag;
        }
    }

    @Override
    public String getUserToken(User user) {
      return userDao.getUserToken(user);
    }
}
