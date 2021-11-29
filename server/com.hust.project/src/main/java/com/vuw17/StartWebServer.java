package com.vuw17;


import com.vuw17.common.Common;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2   // http://localhost:8080/swagger-ui.html#
public class StartWebServer {
    public static void main(String[] args){
        SpringApplication.run(StartWebServer.class, args);
        System.out.println(Common.GeneratePassword("admin"));
        System.out.println(System.currentTimeMillis());
    }
}