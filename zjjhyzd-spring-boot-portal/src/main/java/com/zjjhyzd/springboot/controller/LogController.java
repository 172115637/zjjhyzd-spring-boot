package com.zjjhyzd.springboot.controller;

import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import com.zjjhyzd.springboot.model.DataModel;
import com.zjjhyzd.springboot.factory.ResponseEntityFactory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.http.ResponseEntity;
import com.zjjhyzd.springboot.service.LogService;
import com.zjjhyzd.springboot.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lizhixiang
 * @since 2021-02-01
 */
@RestController
@RequestMapping("/api/log")
@Api(value = "LogController",tags = "")
public class LogController {


    @Autowired
    private LogService logService;

    @ApiOperation(value = "分页查询")
    @GetMapping(value = "/")
    public ResponseEntity<DataModel> list(@RequestParam(required = false,defaultValue = "1") Integer current, @RequestParam(required = false,defaultValue = "10") Integer pageSize) {
        Page<Log> aPage = logService.page(new Page<>(current, pageSize));
        return ResponseEntityFactory.success(aPage);
    }

    @ApiOperation(value = "条件查询")
    @PostMapping(value = "/")
    public ResponseEntity<DataModel> list(@RequestBody Log params, @RequestParam(required = false,defaultValue = "1") Integer current, @RequestParam(required = false,defaultValue = "10") Integer pageSize) {
        Page<Log> aPage = logService.page(new Page<>(current, pageSize), new QueryWrapper<>(params));
        return ResponseEntityFactory.success(aPage);
    }

    @ApiOperation(value = "查询详情")
    @GetMapping(value = "/{id}")
    public ResponseEntity<DataModel> getById(@PathVariable("id") String id) {
        return ResponseEntityFactory.success(logService.getById(id));
    }

    @ApiOperation(value = "新增")
    @PostMapping(value = "/create")
    public ResponseEntity<DataModel> create(@RequestBody Log params) {
        logService.save(params);
        return ResponseEntityFactory.success("created successfully");
    }

    @ApiOperation(value = "删除")
    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<DataModel> delete(@PathVariable("id") String id) {
        logService.removeById(id);
        return ResponseEntityFactory.success("deleted successfully");
    }

    @ApiOperation(value = "更新")
    @PostMapping(value = "/update")
    public ResponseEntity<DataModel> delete(@RequestBody Log params) {
        logService.updateById(params);
        return ResponseEntityFactory.success("updated successfully");
    }
}
