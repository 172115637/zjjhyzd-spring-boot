package com.zjjhyzd.springboot.config.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjjhyzd.springboot.model.UserRole;
import com.zjjhyzd.springboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<com.zjjhyzd.springboot.model.User> userWrapper = new QueryWrapper<com.zjjhyzd.springboot.model.User>().eq("username",username);
        com.zjjhyzd.springboot.model.User user = userService.getOne(userWrapper);
        if (user==null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        QueryWrapper<UserRole> userRoleWrapper = new QueryWrapper<UserRole>().eq("user_id",user.getId());
        UserRole userRole = userRoleService.getOne(userRoleWrapper);

        Set<String> roleIdSet = roleService.getParentIdSet(userRole.getRoleId(),new HashSet<>());
        Set<String> permissionIdSet = rolePermissionService.getPermissionIdSetByRoleId(roleIdSet);
        Set<String> permissionList = new HashSet<>();
        permissionIdSet.forEach(id->permissionService.getParentEnnameSet(id,permissionList));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        permissionList.forEach(permission->grantedAuthorities.add(new SimpleGrantedAuthority(permission)));

        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
