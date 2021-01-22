package com.zjjhyzd.springboot;

import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.github.davidfantasy.mybatisplus.generatorui.GeneratorConfig;
import com.github.davidfantasy.mybatisplus.generatorui.MybatisPlusToolsApplication;
import com.github.davidfantasy.mybatisplus.generatorui.mbp.NameConverter;

public class GeneratorMain {
    public static void main(String[] args) {
        GeneratorConfig config = GeneratorConfig.builder().jdbcUrl("jdbc:mysql://localhost:3306/rbac?useSSL=false")
                .userName("root")
                .password("123456")
                .driverClassName("com.mysql.jdbc.Driver")
                .dateType(DateType.TIME_PACK)
                .basePackage("com.zjjhyzd.springboot")
                .nameConverter(new NameConverter() {
                    /**
                     * 自定义Service类文件的名称规则
                     */
                    @Override
                    public String serviceNameConvert(String tableName) {
                        return this.entityNameConvert(tableName) + "Service";
                    }
                    /**
                     * 自定义Controller类文件的名称规则
                     */
                    @Override
                    public String controllerNameConvert(String tableName) {
                        return this.entityNameConvert(tableName) + "Controller";
                    }
                })
                .port(8068)
                .build();
        MybatisPlusToolsApplication.run(config);
    }
}
