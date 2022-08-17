package com.cc.bookmanger.service;

import com.cc.bookmanger.entity.BookInfo;

import java.util.List;

public interface BookInfoService {

    /**
     * 获取图书总量
     * @return
     */
    Integer getCount();

    /**
     * 查询图书信息
     * @return
     */
    List<BookInfo> queryBookInfos();

    /**
     * 分页查询图书信息
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<BookInfo> page(Integer pageNo, Integer pageSize);

    /**
     * 添加图书
     * @param bookInfo
     * @return
     */
    Boolean addBook(BookInfo bookInfo);
}
