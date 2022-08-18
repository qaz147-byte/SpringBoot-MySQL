package com.cc.bookmanger.mapper;

import com.cc.bookmanger.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserMapper文件
 */
public interface UserMapper {

    /**
     * @param userName
     * @param userPassword
     * @param isAdmin
     * @return
     */
    User selectByUsernameAndPasswordAndIsAdmin(@Param("userName") String userName,
                                               @Param("userPassword") String userPassword,
                                               @Param("isAdmin") Integer isAdmin);

    /**
     * @param userById
     * @return
     */
    User getUserById(@Param("userId") Integer userById);

    /**
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     * @param user
     * @return
     */
    Boolean insertSelective(User user);

    /**
     * @return
     */
    List<User> getUserAll();

    /**
     * @param user
     * @return
     */
    Boolean updateUserByKey(User user);
}
