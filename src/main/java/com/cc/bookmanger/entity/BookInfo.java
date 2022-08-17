package com.cc.bookmanger.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookInfo {
    private Integer bookid;

    private String bookname;

    private String bookauthor;

    private BigDecimal bookprice;

    private Integer booktypeid;

    private String booktypename;

    private String bookdesc;

    private Byte isborrowed;

    private String bookimg;
}
