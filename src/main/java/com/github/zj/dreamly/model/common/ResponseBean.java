package com.github.zj.dreamly.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ResponseBean
 * @author 苍海之南
 * @date 2018/8/30 11:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBean {
    /**
     * HTTP状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private Object data;
}
