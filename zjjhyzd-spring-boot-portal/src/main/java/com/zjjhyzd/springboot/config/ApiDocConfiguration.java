package com.zjjhyzd.springboot.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@EnableKnife4j
@EnableOpenApi
public class ApiDocConfiguration {


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zjjhyzd.springboot.controller")
                        .or(RequestHandlerSelectors.basePackage("com.zjjhyzd.springboot.config.controller")))
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        // 获取工程名称
        String projectName = System.getProperty("user.dir");
        return new ApiInfoBuilder()
                .title(projectName.substring(projectName.lastIndexOf("\\") + 1) + " API接口文档")
                .contact(new Contact("lizhixiang", "", "admin@zjjhyzd.com"))
                .version("1.0")
                .description("API文档")
                .build();
    }
}
