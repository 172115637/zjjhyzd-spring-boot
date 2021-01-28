package com.zjjhyzd.springboot.config;

import com.zjjhyzd.springboot.config.handlers.AuthenticationEntryPointHandler;
import com.zjjhyzd.springboot.config.handlers.FormLoginAuthenticationFailureHandler;
import com.zjjhyzd.springboot.config.handlers.FormLoginAuthenticationSuccessHandler;
import com.zjjhyzd.springboot.config.service.NormalUserDetailsServiceImpl;
import com.zjjhyzd.springboot.config.filter.JwtAuthenticationTokenFilter;
import com.zjjhyzd.springboot.config.handlers.AuthenticationAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationAccessDeniedHandler accessDeniedHandler;

    @Autowired
    AuthenticationEntryPointHandler authenticationEntryPointHandler;

    @Autowired
    FormLoginAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    FormLoginAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    @Qualifier("normalUserDetailsServiceImpl")
    NormalUserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPointHandler).accessDeniedHandler(accessDeniedHandler)
                .and().formLogin().failureHandler(authenticationFailureHandler).successHandler(authenticationSuccessHandler)
                .and().authorizeRequests().antMatchers("/login", "/logout", "/test/**").permitAll().antMatchers("/student/list").hasRole("ADMIN")
                .and().addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}
