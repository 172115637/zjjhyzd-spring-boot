package com.zjjhyzd.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjjhyzd.springboot.mapper.PermissionMapper;
import com.zjjhyzd.springboot.model.Permission;
import com.zjjhyzd.springboot.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author lizhixiang
 * @since 2021-01-18
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public Set<String> getParentEnnameSet(String permissionId, Set<String> idSet) {
        QueryWrapper<Permission> wrapper = new QueryWrapper<Permission>().eq("id", permissionId).select("id", "parent_id", "enname");
        Permission one = this.getOne(wrapper);
        if (Objects.nonNull(one)){
            idSet.add(one.getEnname());
            if (Objects.nonNull(one.getParentId())){
                this.getParentEnnameSet(one.getParentId(),idSet);
            }
        }
        return idSet;
    }
}
