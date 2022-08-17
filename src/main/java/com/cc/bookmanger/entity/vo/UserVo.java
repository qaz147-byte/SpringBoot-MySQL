package com.cc.bookmanger.entity.vo;

import lombok.Data;

@Data
public class UserVo {

    private Integer userid;

    private String username;

    private String userpassword;

    private Integer isadmin;
}
