package indi.harry.music.service.impl;

import indi.harry.music.common.ServerResponseResult;
import indi.harry.music.entity.User;
import indi.harry.music.mapper.UserMapper;
import indi.harry.music.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Avicii4 at 2022/1/7.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public ServerResponseResult<User> login(String username, String password) {
        int userCount = userMapper.checkUsername(username);
        if (userCount == 0){
            return ServerResponseResult.responseErrorMessage("用户不存在");
        }
        User user = userMapper.selectLogin(username,password);
        if(user== null){
            return ServerResponseResult.responseErrorMessage("密码输入错误");
        } else {
            return ServerResponseResult.responseSuccess("登录成功",user);
        }
    }

    @Override
    public  ServerResponseResult<User> register(String username, String password) {
        int userCount = userMapper.checkUsername(username);
        if(userCount == 1){
            return ServerResponseResult.responseErrorMessage("用户已存在");
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setCreateTime(new Date());
        newUser.setUpdateTime(new Date());
        int insertCount = userMapper.insertSelective(newUser);
        if(insertCount ==1){
            return ServerResponseResult.responseSuccess("注册成功",newUser);
        } else {
            return ServerResponseResult.responseErrorMessage("注册失败");
        }
    }

    @Override
    public ServerResponseResult<Boolean> modifyPassword(String username, String password) {
        int userCount = userMapper.checkUsername(username);
        if (userCount == 0){
            return ServerResponseResult.responseErrorMessage("用户不存在");
        }

        int modifyResult = userMapper.updatePassword(username,password);
        if(modifyResult ==1){
            return ServerResponseResult.responseSuccess(true);
        } else {
            return ServerResponseResult.responseErrorMessage("密码修改失败");
        }
    }
}
