package com.github.zj.dreamly.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * RolePermission
 * @author 苍海之南
 * @date 2018/8/31 14:43
 */
@Table(name = "role_permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission implements Serializable {

    private static final long serialVersionUID = -8564770707000796503L;

    /**
     * ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 权限id
     */
    @Column(name = "permission_id")
    private Integer permissionId;
}