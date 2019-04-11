package sprintBootdemoproject;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.context.web.SpringBootServletInitializer;

import org.springframework.context.annotation.ComponentScan;

import org.springframework.context.annotation.Configuration;

@Configuration

@ComponentScan

@EnableAutoConfiguration

@SpringBootApplication
public class mainJavaClass extends SpringBootServletInitializer  {
	
	public static void main(String[] args) {

        SpringApplication.run(mainJavaClass.class, args);

    }



    @Override

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(mainJavaClass.class);

    }

}
