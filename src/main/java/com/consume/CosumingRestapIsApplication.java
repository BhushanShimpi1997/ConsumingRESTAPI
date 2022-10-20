package com.consume;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.consume.proxy")
public class CosumingRestapIsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CosumingRestapIsApplication.class, args);
	}

}
