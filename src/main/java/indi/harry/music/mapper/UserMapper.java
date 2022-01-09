package indi.harry.music.mapper;

import indi.harry.music.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    // 查询是否有该用户
    int checkUsername(String username);

    // 校验用户名与密码匹配
    User selectLogin(@Param("username") String username, @Param("password") String password);

    // 插入新用户
    int insertSelective(User user);

    // 修改密码
    int updatePassword(@Param("username") String username,@Param("password") String password);

}