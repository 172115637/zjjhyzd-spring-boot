package com.zjjhyzd.springboot.controller;

import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjjhyzd.springboot.model.DataModel;
import com.zjjhyzd.springboot.factory.ResponseEntityFactory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.http.ResponseEntity;
import com.zjjhyzd.springboot.miniapp.service.RoleService;
import com.zjjhyzd.springboot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author lizhixiang
 * @since 2021-01-28
 */
@RestController
@RequestMapping("/api/role")
public class RoleController {


    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/")
    public ResponseEntity<DataModel> list(@RequestParam(required = false,defaultValue = "1") Integer current, @RequestParam(required = false,defaultValue = "10") Integer pageSize) {
        Page<Role> aPage = roleService.page(new Page<>(current, pageSize));
        return ResponseEntityFactory.success(aPage);
    }

    @PostMapping(value = "/")
    public ResponseEntity<DataModel> list(@RequestBody Role params,@RequestParam(required = false,defaultValue = "1") Integer current, @RequestParam(required = false,defaultValue = "10") Integer pageSize) {
        Page<Role> aPage = roleService.page(new Page<>(current, pageSize),new QueryWrapper<>(params));
        return ResponseEntityFactory.success(aPage);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DataModel> getById(@PathVariable("id") String id) {
        return ResponseEntityFactory.success(roleService.getById(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<DataModel> create(@RequestBody Role params) {
        roleService.save(params);
        return ResponseEntityFactory.success("created successfully");
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<DataModel> delete(@PathVariable("id") String id) {
        roleService.removeById(id);
        return ResponseEntityFactory.success("deleted successfully");
    }

    @PostMapping(value = "/update")
    public ResponseEntity<DataModel> delete(@RequestBody Role params) {
        roleService.updateById(params);
        return ResponseEntityFactory.success("updated successfully");
    }
}
