package com.github.zj.dreamly.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.zj.dreamly.model.valid.group.UserLoginValidGroup;
import com.github.zj.dreamly.model.valid.group.UserEditValidGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * User
 * @author 苍海之南
 * @date 2018/8/31 14:43
 */
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 3342723124953988435L;

    /**
     * ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 帐号
     */
    @NotBlank(message = "帐号不能为空", groups = { UserLoginValidGroup.class, UserEditValidGroup.class })
    private String account;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = { UserLoginValidGroup.class, UserEditValidGroup.class })
    private String password;

    /**
     * 昵称
     */
    @NotBlank(message = "用户名不能为空", groups = { UserEditValidGroup.class })
    private String username;

    /**
     * 注册时间
     */
    @Column(name = "reg_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date regTime;
}