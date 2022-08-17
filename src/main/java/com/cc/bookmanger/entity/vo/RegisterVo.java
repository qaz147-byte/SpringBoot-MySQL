package com.cc.bookmanger.entity.vo;

import lombok.Data;

@Data
public class RegisterVo {

    private String userName;

    private String password;

    private Integer isAdmin;
}
