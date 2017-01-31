package edu.tstc.yy.serviceImp;

import edu.tstc.yy.dao.UserInfoDao;
import edu.tstc.yy.model.User;
import edu.tstc.yy.model.UserInfo;
import edu.tstc.yy.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by w_2 on 2016-09-28.
 */
@Service("userInfoService")
@Transactional(rollbackFor = Exception.class)
public class UserInfoServiceImp implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;
    @Override
    public Boolean addUserInfo(UserInfo userInfo) {

        return userInfoDao.insertInfo(userInfo);
    }

    @Override
    public UserInfo getUserInfo(User user) {
        return userInfoDao.getUserInfo(user);
    }

    @Override
    public Boolean updateUserInfo(UserInfo userInfo) {
        return userInfoDao.upDateInfo(userInfo);
    }
}
