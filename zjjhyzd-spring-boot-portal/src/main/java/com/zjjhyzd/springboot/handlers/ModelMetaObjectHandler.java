package com.zjjhyzd.springboot.handlers;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class ModelMetaObjectHandler implements MetaObjectHandler {

    public static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        LocalDateTime now = LocalDateTime.now();
        this.strictInsertFill(metaObject, "createAt", String.class, now.format(format));
        this.strictInsertFill(metaObject, "createBy", String.class, SecurityContextHolder.getContext().getAuthentication().getName());
        this.strictInsertFill(metaObject, "updateAt", String.class, now.format(format));
        this.strictInsertFill(metaObject, "updateBy", String.class, SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        LocalDateTime now = LocalDateTime.now();
        this.strictUpdateFill(metaObject, "updateAt", String.class, now.format(format));
        this.strictUpdateFill(metaObject, "updateBy", String.class, SecurityContextHolder.getContext().getAuthentication().getName());
    }
}