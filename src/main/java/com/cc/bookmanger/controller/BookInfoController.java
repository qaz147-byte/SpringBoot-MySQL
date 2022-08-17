package com.cc.bookmanger.controller;

import com.cc.bookmanger.entity.BookInfo;
import com.cc.bookmanger.service.BookInfoService;
import com.cc.bookmanger.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bookInfo")
public class BookInfoController {
    @Autowired
    private BookInfoService bookInfoService;

    /**
     * 查询图书数量
     * @return
     */
    @GetMapping(value = "/getCount")
    public Result getCount(){
        Integer count = bookInfoService.getCount();
        return Result.ok().data("count",count);
    }

    /**
     * 查询图书信息
     * @return
     */
    @GetMapping(value = "/queryBookInfos")
    public Result queryBookInfos(){
        List<BookInfo> bookInfos = bookInfoService.queryBookInfos();
        return Result.ok().data("bookInfos",bookInfos);
    }

    /**
     * 分页查询图书信息
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/queryBookInfosByPage")
    public Result queryBookInfosByPage(@RequestParam Integer pageNo,
                                       @RequestParam Integer pageSize){

        List<BookInfo> bookInfoList = bookInfoService.page(pageNo,pageSize);
        Integer count = bookInfoService.getCount();
        return Result.ok().data("bookInfoList",bookInfoList).data("count",count).data("pageNo",pageNo).data("pageSize",pageSize);
    }

    /**
     * 增加图书
     * @param bookInfo
     * @return
     */
    @PostMapping(value = "/addBook")
    public Boolean addBook(@RequestBody BookInfo bookInfo){
        Boolean b = bookInfoService.addBook(bookInfo);
        return b;
    }

}
