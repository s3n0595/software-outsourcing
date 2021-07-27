package com.cykj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guoquansen
 * @date 2021/7/27 8:58 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T      data;
    public CommonResult(Integer code,String message) {
        this(code,message,null);
    }
}
