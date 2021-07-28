package com.cykj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.cykj.mapper")
@EnableTransactionManagement(proxyTargetClass = true)
public class SysManApplication9091 {
    public static void main(String[] args) {
        SpringApplication.run(SysManApplication9091.class, args);
    }
}
