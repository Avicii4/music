package indi.harry.music.controller;

import indi.harry.music.common.ServerResponseResult;
import indi.harry.music.entity.User;
import indi.harry.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户行为Controller
 * Created by Avicii4 at 2022/1/7.
 */

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录成功/失败信息
     */
    @PostMapping("login")
    public ServerResponseResult<User> login(@RequestParam("username") String username,
                                            @RequestParam("password") String password) {
        return userService.login(username, password);
    }

    /**
     * 用户注册
     *
     * @param username 用户名
     * @param password 密码
     * @return 注册成功/失败信息
     */
    @PostMapping("register")
    public ServerResponseResult<User> register(@RequestParam("username") String username,
                                               @RequestParam("password") String password) {
        return userService.register(username, password);
    }

    /**
     * 修改密码
     *
     * @param username 用户名
     * @param password 密码
     * @return 修改密码成功/失败信息
     */
    @PostMapping("modify")
    public ServerResponseResult<Boolean> modifyPassword(@RequestParam("username") String username,
                                                        @RequestParam("password") String password) {
        return userService.modifyPassword(username, password);
    }

}
