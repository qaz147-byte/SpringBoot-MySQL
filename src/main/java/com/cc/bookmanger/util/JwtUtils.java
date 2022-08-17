package com.cc.bookmanger.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtUtils {
    //使用前提请先导包jjtw包

    //token过期时间
    public static final long EXPIRE = 1000 * 60 * 60 * 24;

    //秘钥，每个公司生成规则不一样
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    //生成token字符串方法
    public static String getJwtToken(Integer userId, String username) {
        String JwtToken = Jwts.builder()
                //设置jwt头信息，红色部分，内容固定，不需要改
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")

                .setSubject("springboot-mysql-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))//设置过期时间

                //设置token主体部分，存储用户信息，可设置多个值
                .claim("userId", userId)
                .claim("username",username)

                //设置签名哈希（防伪标志）
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();

        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     *
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) return false;
        try {
            //根据设置的防伪码解析token
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 根据token获取会员userId
     *
     * @param request
     * @return
     */
    public static Integer getUserByToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if (ObjectUtils.isEmpty(jwtToken)) {
            return Integer.valueOf("");
        }
        //根据设置的防伪码解析token，获取对象
        Jws<Claims> claimsJws =
                Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        //获取token有效载荷【用户信息】
        Claims claims = claimsJws.getBody();
        return (Integer) claims.get("userId");
    }


}
