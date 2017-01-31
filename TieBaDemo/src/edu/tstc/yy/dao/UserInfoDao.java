package edu.tstc.yy.dao;

import edu.tstc.yy.model.User;
import edu.tstc.yy.model.UserInfo;

/**
 * Created by w_2 on 2016-09-25.
 * 该映射接口用于对userinfotable表中的数据进行操作
 */
public interface UserInfoDao {
    /**
     * 向userinfotable中插入新的用户信息
     * @param userInfo 包含用户提交的用户信息
     * @return 成功返回 true,失败返回false
     */
    public Boolean insertInfo(UserInfo userInfo);

    /**
     * 向userinfotable查询指定userId的用户信息
     * @param user 包含用户要查询的userId
     * @return 若存在相应用户，返回UserInfo封装的用户信息；否则返回null
     */
    public UserInfo getUserInfo(User user);

    /**
     * 更新用户信息
     * @param userInfo 更新后的用户信息
     * @return 成功返回 true,失败返回false
     */
    public Boolean upDateInfo(UserInfo userInfo);
}
