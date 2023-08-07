package com.endava.bugHunting.bug_hunting.security;

import com.endava.bugHunting.bug_hunting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Autowired
    private UserService userService;

    @Bean
    public FilterRegistrationBean logginFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean<>();
        RequestFilter filter = new RequestFilter(userService);
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/users/*", "/location/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }

}
