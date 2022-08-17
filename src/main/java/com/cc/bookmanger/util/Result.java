package com.cc.bookmanger.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一结果集
 * @author cheng.chen8
 */

@Data
public class Result {

    private Boolean success;

    private Integer code;

    private String message;

    private Map<String ,Object> data = new HashMap<String, Object>();

    public Result() {
    }

    public static Result ok(){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage("请求成功");
        return result;
    }

    public static Result error(){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR.getCode());
        result.setMessage("请求失败");
        return result;
    }

    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result data(String key,Object value){
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String,Object> map){
        this.setData(map);
        return this;
    }
}
