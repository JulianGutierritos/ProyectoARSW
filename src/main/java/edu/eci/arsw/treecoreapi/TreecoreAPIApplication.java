package edu.eci.arsw.treecoreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw.treecore"})
public class TreecoreAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreecoreAPIApplication.class, args);
	}
}