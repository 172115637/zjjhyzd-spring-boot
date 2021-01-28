package com.zjjhyzd.springboot.miniapp.service;

import com.zjjhyzd.springboot.model.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author lizhixiang
 * @since 2021-01-18
 */
public interface PermissionService extends IService<Permission> {
    Set<String> getParentEnnameSet(String permissionId, Set<String> ennames);
}
