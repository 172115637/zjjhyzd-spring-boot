package com.zjjhyzd.springboot.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public GroupedOpenApi permission() {
        return GroupedOpenApi.builder()
                .group("权限")
                .pathsToMatch("/api/permission/**")
                .build();
    }

    @Bean
    public GroupedOpenApi role() {
        return GroupedOpenApi.builder()
                .group("角色")
                .pathsToMatch("/api/role/**")
                .build();
    }

    @Bean
    public GroupedOpenApi rolePermission() {
        return GroupedOpenApi.builder()
                .group("角色权限")
                .pathsToMatch("/api/role-permission/**")
                .build();
    }
}
