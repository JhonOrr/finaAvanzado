package com.example.msorregoJesus.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.example.*")
@EntityScan("com.example.*")
@EnableFeignClients("com.example.*")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@EnableJpaRepositories("com.example.*")
public class ApplicationLauncher {
    public static void main(String[] args){
        SpringApplication.run(ApplicationLauncher.class, args);
    }
}
