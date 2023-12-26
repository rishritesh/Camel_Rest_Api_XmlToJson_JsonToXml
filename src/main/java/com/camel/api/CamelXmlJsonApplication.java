package com.camel.api;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@EnableAutoConfiguration
public class CamelXmlJsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelXmlJsonApplication.class, args);
	}


}
