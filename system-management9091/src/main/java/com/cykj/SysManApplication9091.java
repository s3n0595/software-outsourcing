package com.cykj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cykj.mapper")
public class SysManApplication9091 {

    public static void main(String[] args) {
        SpringApplication.run(SysManApplication9091.class, args);
    }

}
