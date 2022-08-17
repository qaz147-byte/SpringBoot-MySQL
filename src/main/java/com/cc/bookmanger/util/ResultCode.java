package com.cc.bookmanger.util;

import lombok.Getter;

/**
 * 编码枚举类
 * @author cheng.chen8
 */

@Getter
public enum ResultCode{

    SUCCESS("success",20000),

    ERROR("error",20001);

    private String message;

    private Integer code;

    ResultCode(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
}
