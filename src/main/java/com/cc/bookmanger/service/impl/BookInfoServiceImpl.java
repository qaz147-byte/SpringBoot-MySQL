package com.cc.bookmanger.service.impl;

import com.cc.bookmanger.entity.BookInfo;
import com.cc.bookmanger.mapper.BookInfoMapper;
import com.cc.bookmanger.service.BookInfoService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 图书信息接口实现类
 * @author cheng.chen8
 */
@Service
public class BookInfoServiceImpl implements BookInfoService {

    @Resource
    private BookInfoMapper bookInfoMapper;

    /**
     * 获取全部图书
     * @return
     */
    @Override
    public Integer getCount() {
        return bookInfoMapper.selectCount();
    }

    /**
     * 查询全部图书信息
     * @return
     */
    @Override
    public List<BookInfo> queryBookInfos() {
        return bookInfoMapper.selectAll();
    }

    /**
     * 分页查询图书
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<BookInfo> page(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return bookInfoMapper.bookPage();
    }

    /**
     * 增加图书
     * @param bookInfo
     * @return
     */
    @Override
    public Boolean addBook(BookInfo bookInfo) {
        return bookInfoMapper.addBook(bookInfo);
    }

}
