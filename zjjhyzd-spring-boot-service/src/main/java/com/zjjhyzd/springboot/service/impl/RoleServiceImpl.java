package com.zjjhyzd.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjjhyzd.springboot.mapper.RoleMapper;
import com.zjjhyzd.springboot.model.Role;
import com.zjjhyzd.springboot.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author lizhixiang
 * @since 2021-01-18
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    /**
     * 使用迭代查询的思想，自己调自己
     *
     * @param roleId 权限组ID
     * @param idSet  所有上级ID
     * @return 所有上级ID
     */
    @Override
    public Set<String> getParentIdSet(String roleId, Set<String> idSet) {
        idSet.add(roleId);
        QueryWrapper<Role> wrapper = new QueryWrapper<Role>().eq("id", roleId).select("id", "parent_id", "enname");
        Role one = this.getOne(wrapper);
        if (Objects.nonNull(one)) {
            idSet.add(one.getId());
            if (Objects.nonNull(one.getParentId())) {
                this.getParentIdSet(one.getParentId(), idSet);
            }
        }
        return idSet;
    }

    @Override
    public Role getRoleAdmin() {
        return this.getOne(new QueryWrapper<Role>().eq("enname", "ROLE_ADMIN"));
    }

    @Override
    public Role getRoleUser() {
        return this.getOne(new QueryWrapper<Role>().eq("enname", "ROLE_USER"));
    }
}
