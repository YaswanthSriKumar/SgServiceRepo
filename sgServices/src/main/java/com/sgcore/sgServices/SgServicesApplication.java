package com.sgcore.sgServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SgServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgServicesApplication.class, args);
	}

}
