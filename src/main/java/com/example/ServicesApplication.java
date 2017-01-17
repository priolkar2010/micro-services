package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

@SpringBootApplication
@EnableHystrix
@RestController
@EnableEurekaClient
@RefreshScope
public class ServicesApplication implements GreetingController {

	@Autowired
	@Lazy
	private EurekaClient eurekaClient;
	
	@Value("${spring.application.name}")
	private String myApps;
	
	public static void main(String[] args) {
		SpringApplication.run(ServicesApplication.class, args);
	}
	
	@Value("${message}")
	private String message;
	
	@Override
	public String greeting(){
		String x = System.getProperty("server.port");
		System.out.println(x);
		return String.format("Hello"+ message+ eurekaClient.getApplication(myApps).getName());
	}
}
