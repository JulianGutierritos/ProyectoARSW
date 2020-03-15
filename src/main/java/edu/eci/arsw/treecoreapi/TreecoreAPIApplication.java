package edu.eci.arsw.treecoreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
@MapperScan("edu.eci.arsw.treecore.persistence.mappers")
public class TreecoreAPIApplication {
	public static void main(String[] args) {
		SpringApplication.run(TreecoreAPIApplication.class, args);
	}
}