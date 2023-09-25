package com.dage.quality;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
public class QualityApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(QualityApplication.class);
	}
	public static void main(String[] args) {SpringApplication.run(QualityApplication.class,args);}

}
