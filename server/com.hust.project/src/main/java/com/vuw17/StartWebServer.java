package com.vuw17;


import com.vuw17.common.Common;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
@EnableSwagger2   // http://localhost:8080/swagger-ui.html#
public class StartWebServer {
    public static void main(String[] args){
        SpringApplication.run(StartWebServer.class, args);
        System.out.println(Common.GeneratePassword("admin"));
//        LocalDate localDate = LocalDate.now();
//        localDate.plusDays(5);
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis() + (80400000 * 2));
        System.out.println("=========================================");
        System.out.println("Real = "+(double)86400001/86400000);
        BigDecimal tinhTien = new BigDecimal(Math.ceil((86400001/86400000) + 1));
        System.out.println("Fake = "+tinhTien);

    }
}