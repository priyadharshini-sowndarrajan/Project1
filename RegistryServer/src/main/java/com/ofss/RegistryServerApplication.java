package com.ofss;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@SpringBootApplication
@EnableEurekaServer // This annotation is required in case of registry server
public class RegistryServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(RegistryServerApplication.class, args);
		System.out.println("Registry Server Started Successfully");
	}
}
