package com.mmp.xmlproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class XmlprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmlprojectApplication.class, args);
	}

}
