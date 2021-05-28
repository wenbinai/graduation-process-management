package com.nefu.se.graduationprocessmanagement.vo;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO {
    private int code;
    private String msg;
    private Map<String, Object> data;


    public static ResultVO success(Map data) {
        return ResultVO.builder()
                .code(200)
                .data(data)
                .msg("请求成功")
                .build();
    }

    public static ResultVO fail(int code, String msg) {
        return ResultVO.builder()
                .code(code)
                .msg(msg)
                .build();
    }

    public static ResultVO unAuthorizationResultVO(String msg) {
        return ResultVO.builder()
                .code(403)
                .msg(msg)
                .build();
    }


    public static ResultVO failClientResultVO() {
        return ResultVO.builder()
                .code(400)
                .msg("客户端传入数据错误")
                .build();
    }
}
