package com.github.zj.dreamly.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.zj.dreamly.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 *
 * @author 苍海之南
 * @date 2018/8/30 10:34
 */
@Setter
@Getter
@Table(name = "user")
public class UserDto extends User {
    /**
     * 登录时间
     */
    @Transient
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date loginTime;
}
