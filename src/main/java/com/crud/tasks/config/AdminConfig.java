package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AdminConfig {
    @Value("${admin.mail}")
    private String adminMail;

    //@Value("${admin_name}")
    private String adminName;
}