package com.github.zj.dreamly.mapper;

import com.github.zj.dreamly.model.RoleDto;
import com.github.zj.dreamly.model.UserDto;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * RoleMapper
 * @author 苍海之南
 * @date 2018/8/31 14:42
 */
@Repository
public interface RoleMapper extends Mapper<RoleDto> {
    /**
     * 根据User查询Role
     * @param userDto {@link UserDto}
     * @return java.util.List<RoleDto>
     * @author 苍海之南
     * @date 2018/8/31 11:30
     */
    List<RoleDto> findRoleByUser(UserDto userDto);
}