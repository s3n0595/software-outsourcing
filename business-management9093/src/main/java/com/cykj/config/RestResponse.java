package com.cykj.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @version 1.0
 * @author: Jing
 * @date: 2021/8/10 15:35
 */
public class RestResponse{
    public static final String SUCCESS_MSG = "操作成功";
    public static final String FAILURE_MSG = "操作失败";
    public static final Integer SUCCESS_CODE = 0;
    public static final Integer FAILURE_CODE = 300;

    private Integer code;

    private String msg;
    private String data;

    public RestResponse() {
    }

    public RestResponse(Integer code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RestResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        data = null;
    }

    public static RestResponse buildFailureResp(Exception e) {
        return new RestResponse(RestResponse.FAILURE_CODE, e.getMessage());
    }

    public static RestResponse buildFailureResp(String msg) {
        return new RestResponse(RestResponse.FAILURE_CODE, msg);
    }

    public static RestResponse buildSuccessResp(String data) {
        return new RestResponse(RestResponse.SUCCESS_CODE, RestResponse.SUCCESS_MSG, data);
    }

    public static RestResponse buildSuccessResp() {
        return new RestResponse(RestResponse.SUCCESS_CODE, RestResponse.SUCCESS_MSG);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
