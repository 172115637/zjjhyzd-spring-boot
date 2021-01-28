package com.zjjhyzd.springboot.miniapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjjhyzd.springboot.model.RolePermission;
import com.zjjhyzd.springboot.miniapp.mapper.RolePermissionMapper;
import com.zjjhyzd.springboot.miniapp.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @author lizhixiang
 * @since 2021-01-18
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    /**
     *
     * @param collection roleId list
     * @return
     */
    @Override
    public Set<String> getPermissionIdSetByRoleId(Collection<String> collection) {
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<RolePermission>().in("role_id", collection).select("permission_id");
        List<RolePermission> list = this.list(wrapper);
        Set<String> idSet =  new HashSet<>();
        list.forEach(item-> idSet.add(item.getPermissionId()));
        return idSet;
    }
}
