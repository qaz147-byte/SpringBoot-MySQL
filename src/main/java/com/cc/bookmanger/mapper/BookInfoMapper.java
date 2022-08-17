package com.cc.bookmanger.mapper;


import com.cc.bookmanger.entity.BookInfo;

import java.util.List;

public interface BookInfoMapper {

    Integer selectCount();

    List<BookInfo> selectAll();

    List<BookInfo> bookPage();

    Boolean addBook(BookInfo bookInfo);
}
