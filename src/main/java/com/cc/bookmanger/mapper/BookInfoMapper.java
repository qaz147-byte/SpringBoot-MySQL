package com.cc.bookmanger.mapper;


import com.cc.bookmanger.entity.BookInfo;

import java.util.List;

/**
 * BookInfoMapper
 */
public interface BookInfoMapper {

    /**
     * @return 返回总数目
     */
    Integer selectCount();

    /**
     * @return 返回图书全部信息
     */
    List<BookInfo> selectAll();

    /**
     * @return 分页查询图书
     */
    List<BookInfo> bookPage();

    /**
     * @param bookInfo
     * @return
     */
    Boolean addBook(BookInfo bookInfo);
}
