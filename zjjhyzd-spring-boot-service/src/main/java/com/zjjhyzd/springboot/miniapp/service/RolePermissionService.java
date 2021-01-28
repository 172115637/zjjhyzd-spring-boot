package com.zjjhyzd.springboot.miniapp.service;

import com.zjjhyzd.springboot.model.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.Set;

/**
 * <p>
 * 角色权限表 服务类
 * </p>
 *
 * @author lizhixiang
 * @since 2021-01-18
 */
public interface RolePermissionService extends IService<RolePermission> {

    /**
     * 根据RoleId查询所有关联表中的权限ID
     * @param collection roleId list
     * @return 权限ID Set
     */
    Set<String> getPermissionIdSetByRoleId(Collection<String> collection);
}
