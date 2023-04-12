package com.phoenix.qpproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value={"com.phoenix.qpproject.mapper"})
public class QpProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(QpProjectApplication.class, args);
	}

}
