package com.cc.bookmanger.service.impl;


import com.cc.bookmanger.exception.CustomException;
import com.cc.bookmanger.entity.User;
import com.cc.bookmanger.entity.vo.RegisterVo;
import com.cc.bookmanger.mapper.UserMapper;
import com.cc.bookmanger.service.UserService;
import com.cc.bookmanger.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 用户接口实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userMapper.selectByUsernameAndPasswordAndIsAdmin(user.getUserName(),user.getUserPassword(),user.getIsAdmin());
    }

    @Override
    public void saveUser(String token, User user) {
        // 设置redisTemplate对象key的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // key是token，value是用户保存到redis中，超时时间1小时
        redisTemplate.opsForValue().set(token, user, 1, TimeUnit.HOURS);
    }

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    @Override
    public User getUser(String token) {
        return (User) redisTemplate.opsForValue().get(token);
    }

    /**
     * 生成登录token
     * @param user
     * @return
     */
    @Override
    public String tokenLogin(User user) {
        User loginUser = userService.login(user);
        String username = loginUser.getUserName();
        String userpassword = loginUser.getUserPassword();

        if (username == null || userpassword ==null){
            throw new CustomException(200001, "用户名或者密码为空");
        }

        String token = JwtUtils.getJwtToken(loginUser.getUserId(), loginUser.getUserName());

        // 设置redisTemplate对象key的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // key是token，value是用户保存到redis中，超时时间1小时
        redisTemplate.opsForValue().set(token, user, 1, TimeUnit.HOURS);

        return token;
    }

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    @Override
    public User getById(Integer userId) {
        return userMapper.getUserById(userId);

    }

    /**
     * 注册
     * @param registerVo
     * @return
     */
    @Override
    public Boolean register(RegisterVo registerVo) {
        //获取数据
        String username = registerVo.getUserName();
        String password = registerVo.getPassword();
        Integer isAdmin = registerVo.getIsAdmin();

        User userTmp = userMapper.selectByUsername(username);
        if (userTmp !=null){
            return false;
        }

        //非空判断
        if (ObjectUtils.isEmpty(username) || ObjectUtils.isEmpty(password)){
            throw new CustomException(2002,"数据为空,注册失败");
        }

        User user = new User();
        user.setUserName(username);
        user.setUserPassword(password);
        user.setIsAdmin(isAdmin);
        Boolean aBoolean = userMapper.insertSelective(user);
        return aBoolean;
    }

    /**
     * 获取全部用户
     * @return
     */
    @Override
    public List<User> getAll() {
        List<User> users = userMapper.getUserAll();
        return users;
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @Override
    public Boolean updateUser(User user) {
        Boolean aBoolean = userMapper.updateUserByKey(user);
        return aBoolean;
    }


}
