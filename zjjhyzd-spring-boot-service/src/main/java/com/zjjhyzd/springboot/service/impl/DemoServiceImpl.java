package com.zjjhyzd.springboot.service.impl;

import com.zjjhyzd.springboot.model.Demo;
import com.zjjhyzd.springboot.mapper.DemoMapper;
import com.zjjhyzd.springboot.service.DemoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lizhixiang
 * @since 2021-01-20
 */
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements DemoService {

}
