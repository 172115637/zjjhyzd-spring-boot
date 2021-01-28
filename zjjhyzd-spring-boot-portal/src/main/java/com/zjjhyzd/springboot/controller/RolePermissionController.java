package com.zjjhyzd.springboot.controller;

import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjjhyzd.springboot.model.DataModel;
import com.zjjhyzd.springboot.factory.ResponseEntityFactory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.http.ResponseEntity;
import com.zjjhyzd.springboot.miniapp.service.RolePermissionService;
import com.zjjhyzd.springboot.model.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 角色权限表 前端控制器
 * </p>
 *
 * @author lizhixiang
 * @since 2021-01-28
 */
@RestController
@RequestMapping("/api/role-permission")
public class RolePermissionController {


    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping(value = "/")
    public ResponseEntity<DataModel> list(@RequestParam(required = false,defaultValue = "1") Integer current, @RequestParam(required = false,defaultValue = "10") Integer pageSize) {
        Page<RolePermission> aPage = rolePermissionService.page(new Page<>(current, pageSize));
        return ResponseEntityFactory.success(aPage);
    }

    @PostMapping(value = "/")
    public ResponseEntity<DataModel> list(@RequestBody RolePermission params,@RequestParam(required = false,defaultValue = "1") Integer current, @RequestParam(required = false,defaultValue = "10") Integer pageSize) {
        Page<RolePermission> aPage = rolePermissionService.page(new Page<>(current, pageSize),new QueryWrapper<>(params));
        return ResponseEntityFactory.success(aPage);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DataModel> getById(@PathVariable("id") String id) {
        return ResponseEntityFactory.success(rolePermissionService.getById(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<DataModel> create(@RequestBody RolePermission params) {
        rolePermissionService.save(params);
        return ResponseEntityFactory.success("created successfully");
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<DataModel> delete(@PathVariable("id") String id) {
        rolePermissionService.removeById(id);
        return ResponseEntityFactory.success("deleted successfully");
    }

    @PostMapping(value = "/update")
    public ResponseEntity<DataModel> delete(@RequestBody RolePermission params) {
        rolePermissionService.updateById(params);
        return ResponseEntityFactory.success("updated successfully");
    }
}
