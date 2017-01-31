package edu.tstc.yy.service;

import edu.tstc.yy.model.User;
import edu.tstc.yy.model.UserInfo;

/**
 * Created by w_2 on 2016-09-28.
 */
public interface UserInfoService {
    Boolean addUserInfo(UserInfo userInfo);

    UserInfo getUserInfo(User user);

    Boolean updateUserInfo(UserInfo userInfo);

}
