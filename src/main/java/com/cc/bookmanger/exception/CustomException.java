package com.cc.bookmanger.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException{

    private Integer code;

    private String message;
}
