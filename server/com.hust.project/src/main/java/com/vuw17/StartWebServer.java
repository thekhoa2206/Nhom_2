package com.vuw17;


import com.vuw17.common.Common;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartWebServer {
    public static void main(String[] args){
        SpringApplication.run(StartWebServer.class, args);
//        System.out.println(Common.GeneratePassword("nguyenvana"));
    }
}