package com.zjjhyzd.springboot.service;

import com.zjjhyzd.springboot.model.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author lizhixiang
 * @since 2021-01-18
 */
public interface RoleService extends IService<Role> {

    /**
     * 迭代查询所有上级元素的ID 包括自己
     * @param roleId 权限组ID
     * @param idSet 所有上级ID
     * @return Set
     */
    Set<String> getParentIdSet(String roleId,Set<String> idSet);

    Role getRoleAdmin();

    Role getRoleUser();
}
