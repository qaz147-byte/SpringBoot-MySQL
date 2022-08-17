package com.cc.bookmanger.controller;

import com.cc.bookmanger.entity.User;
import com.cc.bookmanger.entity.vo.RegisterVo;
import com.cc.bookmanger.service.UserService;
import com.cc.bookmanger.util.JwtTokenUtils;
import com.cc.bookmanger.util.JwtUtils;
import com.cc.bookmanger.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 第一种登录并返回token的方法
     * @param user
     * @return
     */
    @RequestMapping(value = "/userLogin")
    public Result userLogin(@RequestBody User user){
        User userObj = userService.login(user);
        if (userObj == null){
            return Result.error().data("error","账号或密码错误");
        }else {
            String token = JwtTokenUtils.createToken(userObj, 60 * 60);
            userService.saveUser(token,userObj);
            return Result.ok().data("token",token);
        }
    }

    /**
     * 第一种根据token返回用户信息
     * @param token
     * @return
     */
    @RequestMapping(value = "/userInfo")
    public Result userInfo(String token){
        //从redis中获取用户信息
        User user = userService.getUser(token);

        if (user == null){
            return Result.error().data("error","获取用户信息失败");
        }else {
            return Result.ok().data("success","获取用户信息成功");
        }
    }

    /**
     * 登录(使用)
     * @param user
     * @return token
     */
    @PostMapping(value = "/login")
    public Result login(@RequestBody User user){
        String token = userService.tokenLogin(user);
        return Result.ok().data("token",token);
    }

    /**
     * 根据token返回用户信息
     * @param request
     * @return
     */
    @GetMapping(value = "getUserInfoByToken")
    public Result getUserInfoByToken(HttpServletRequest request){
        //调用jwt工具类里面的根据request对象，获取头信息，返回用户id
        Integer userId = JwtUtils.getUserByToken(request);

        User user = userService.getById(userId);
        return Result.ok().data("user",user);
    }

    /**
     * 注册
     * @param registerVo
     * @return
     */
    @PostMapping(value = "/register")
    public Boolean register(@RequestBody RegisterVo registerVo){
        Boolean register = userService.register(registerVo);
        return register;
    }

    /**
     * 查询全部用户
     * @return
     */
    @GetMapping(value = "/getAll")
    public Result getAll(){
        List<User> userList = userService.getAll();
        return Result.ok().data("userList",userList);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PutMapping(value = "/updateUser")
    public Result updateUser(@RequestBody User user){
        Boolean b = userService.updateUser(user);
        return Result.ok().success(b);
    }

}
