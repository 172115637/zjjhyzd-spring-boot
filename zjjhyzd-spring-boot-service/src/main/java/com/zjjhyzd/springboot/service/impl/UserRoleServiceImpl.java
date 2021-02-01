package com.zjjhyzd.springboot.service.impl;

import com.zjjhyzd.springboot.mapper.UserRoleMapper;
import com.zjjhyzd.springboot.model.UserRole;
import com.zjjhyzd.springboot.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author lizhixiang
 * @since 2021-01-18
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
