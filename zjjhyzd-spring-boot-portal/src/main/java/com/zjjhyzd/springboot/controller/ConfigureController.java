package com.zjjhyzd.springboot.controller;

import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import com.zjjhyzd.springboot.model.DataModel;
import com.zjjhyzd.springboot.factory.ResponseEntityFactory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.http.ResponseEntity;
import com.zjjhyzd.springboot.service.ConfigureService;
import com.zjjhyzd.springboot.model.Configure;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 《配置表》 前端控制器
 * </p>
 *
 * @author lizhixiang
 * @since 2021-02-18
 */
@RestController
@RequestMapping("/api/configure")
@Api(value = "ConfigureController", tags = "《配置表》")
public class ConfigureController {


    @Autowired
    private ConfigureService configureService;

    @ApiOperation(value = "分页查询")
    @GetMapping(value = "/")
    public ResponseEntity<DataModel> list(@RequestParam(required = false, defaultValue = "1") Integer current, @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Page<Configure> aPage = configureService.page(new Page<>(current, pageSize));
        return ResponseEntityFactory.success(aPage);
    }

    @ApiOperation(value = "条件查询")
    @PostMapping(value = "/")
    public ResponseEntity<DataModel> list(@RequestBody Configure params, @RequestParam(required = false, defaultValue = "1") Integer current, @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Page<Configure> aPage = configureService.page(new Page<>(current, pageSize), new QueryWrapper<>(params));
        return ResponseEntityFactory.success(aPage);
    }

    @ApiOperation(value = "查询详情")
    @GetMapping(value = "/{keywords}")
    public ResponseEntity<DataModel> getById(@PathVariable("keywords") String keywords) {
        return ResponseEntityFactory.success(configureService.getOne(new QueryWrapper<Configure>().eq("keywords", keywords)));
    }

    @ApiOperation(value = "新增")
    @PostMapping(value = "/create")
    public ResponseEntity<DataModel> create(@RequestBody Configure params) {
        configureService.save(params);
        return ResponseEntityFactory.success("created successfully");
    }

    @ApiOperation(value = "删除")
    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<DataModel> delete(@PathVariable("id") String id) {
        configureService.removeById(id);
        return ResponseEntityFactory.success("deleted successfully");
    }

    @ApiOperation(value = "更新")
    @PostMapping(value = "/update")
    public ResponseEntity<DataModel> delete(@RequestBody Configure params) {
        configureService.updateById(params);
        return ResponseEntityFactory.success("updated successfully");
    }
}
