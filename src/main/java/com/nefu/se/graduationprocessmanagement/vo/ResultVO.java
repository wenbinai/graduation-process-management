package com.nefu.se.graduationprocessmanagement.vo;

import java.util.Map;

public class ResultVO {
    private int code;
    private String message;
    private Map<String, Object> data;

    public ResultVO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public ResultVO setMessage(String message) {
        this.message = message;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public ResultVO setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }


    public static ResultVO successResultVO() {
        return new ResultVO(200, "请求成功");
    }

    public static ResultVO unAuthorizationResultVO() {
        return new ResultVO(403, "没有权限");
    }

    public static ResultVO failServerResultVO() {
        return new ResultVO(500, "服务端休息了...");
    }

    public static ResultVO failClientResultVO() {
        return new ResultVO(400, "客户端传入数据错误");
    }
}
