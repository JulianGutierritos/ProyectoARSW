package edu.eci.arsw.treecore.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw.treecore"})
public class TreeCoreAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreeCoreAPIApplication.class, args);
	}

}
