package com.zjjhyzd.springboot.service.impl;

import com.zjjhyzd.springboot.model.User;
import com.zjjhyzd.springboot.mapper.UserMapper;
import com.zjjhyzd.springboot.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lizhixiang
 * @since 2021-01-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
