package com.github.zj.dreamly.mapper;

import com.github.zj.dreamly.model.UserRoleDto;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * UserRoleMapper
 * @author 苍海之南
 * @date 2018/8/31 14:43
 */
@Repository
public interface UserRoleMapper extends Mapper<UserRoleDto> {
}