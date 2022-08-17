package com.cc.bookmanger.service;

import com.cc.bookmanger.entity.User;
import com.cc.bookmanger.entity.vo.RegisterVo;

import java.util.List;

public interface UserService {

    User login(User user);

    void saveUser(String token, User usr);

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    User getUser(String token);

    /**
     * 用户登录并返回token
     * @param user
     * @return
     */
    String tokenLogin(User user);

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    User getById(Integer userId);

    /**
     * 用户注册
     * @param registerVo
     * @return
     */
    Boolean register(RegisterVo registerVo);

    /**
     * 获取全部用户
     * @return
     */
    List<User> getAll();

    /**
     * 修改用户
     * @param user
     * @return
     */
    Boolean updateUser(User user);
}
