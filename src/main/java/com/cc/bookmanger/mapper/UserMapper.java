package com.cc.bookmanger.mapper;

import com.cc.bookmanger.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User selectByUsernameAndPasswordAndIsAdmin(@Param("userName") String userName,
                                               @Param("userPassword") String userPassword,
                                               @Param("isAdmin") Integer isAdmin);

    User getUserById(@Param("userId") Integer userById);

    User selectByUsername(String username);

    Boolean insertSelective(User user);

    List<User> getUserAll();

    Boolean updateUserByKey(User user);
}
