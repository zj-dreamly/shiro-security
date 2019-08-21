package com.github.zj.dreamly.mapper;

import com.github.zj.dreamly.model.PermissionDto;
import com.github.zj.dreamly.model.RoleDto;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * PermissionMapper
 * @author 苍海之南
 * @date 2018/8/31 14:42
 */
@Repository
public interface PermissionMapper extends Mapper<PermissionDto> {
    /**
     * 根据Role查询Permission
     * @param roleDto {@link RoleDto}
     * @return java.util.List<PermissionDto>
     * @author 苍海之南
     * @date 2018/8/31 11:30
     */
    List<PermissionDto> findPermissionByRole(RoleDto roleDto);
}