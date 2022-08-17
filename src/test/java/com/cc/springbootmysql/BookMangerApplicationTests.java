package com.cc.springbootmysql;


import com.cc.bookmanger.entity.BookInfo;
import com.cc.bookmanger.entity.User;
import com.cc.bookmanger.service.BookInfoService;
import com.cc.bookmanger.service.UserService;
import com.cc.bookmanger.util.JwtTokenUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class BookMangerApplicationTests {

    @Resource
    private BookInfoService bookInfoService;

    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
    }

    /**
     * 查询图书数量
     */
    @Test
    public void getCount(){
        Integer count = bookInfoService.getCount();
        System.out.println(count);
    }

    /**
     * 查询图书信息
     */
    @Test
    public void queryBookInfos(){
        List<BookInfo> bookInfoList = bookInfoService.queryBookInfos();
        System.out.println(bookInfoList);
    }

    @Test
    public void queryBookInfosByPage(){
        List<BookInfo> page = bookInfoService.page(1, 10);
        System.out.println(page);
    }

    @Test
    public void addBook(){
        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookid(789);
        bookInfo.setBookname("测试789");
        bookInfo.setBookauthor("cc789");
        bookInfo.setBookprice(BigDecimal.valueOf(10.00));
        bookInfo.setBooktypeid(1);
        bookInfo.setBookdesc("测试一下试试789");
        bookInfo.setIsborrowed((byte) 0);
        bookInfo.setBookimg("https://wangpeng-imgsubmit.oss-cn-hangzhou.aliyuncs.com/BookManager/pictures/1637336151137福尔摩斯.jpg");
        Boolean book = bookInfoService.addBook(bookInfo);
        System.out.println(book);
    }

    @Test
    public void creatToken(){
        User user = new User();
        user.setUserId(5);
        user.setUserName("cc");
        user.setUserPassword("123456");
        user.setIsAdmin(0);
        String token = JwtTokenUtils.createToken(user, 60 * 60);
        System.out.println(token);
    }

    @Test
    public void login(){
        User user = new User();
        user.setUserId(1);
        user.setUserName("admin");
        user.setUserPassword("admin");
        user.setIsAdmin(1);
        User login = userService.login(user);
        String token = JwtTokenUtils.createToken(login, 60 * 60);
        userService.saveUser(token, login);
        System.out.println(token);
    }

    @Test
    public void info(){
        User user = userService.getUser("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc0FkbWluIjoxLCJ1c2VyTmFtZSI6ImFkbWluIiwiZXhwIjoxNjU5NzgwNTkyLCJ1c2VySWQiOjEsImlhdCI6MTY1OTE3NTc5Mn0.zp6lj47cYfCPRav8k3RkK1DBg31DSkLX6a9b38bxFVM");
        System.out.println(user);
    }

    @Test
    public void getUserAll(){
        List<User> all = userService.getAll();
        System.out.println(all);
    }
}
