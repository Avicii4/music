package indi.harry.music.service;

import indi.harry.music.common.ServerResponseResult;
import indi.harry.music.entity.User;

/**
 * 用户行为服务
 */
public interface UserService {

    // 用户登录
    ServerResponseResult<User> login(String username, String password);

    // 用户注册
    ServerResponseResult<User> register(String username,String password);

    // 修改密码
    ServerResponseResult<Boolean> modifyPassword(String username,String password);
}
